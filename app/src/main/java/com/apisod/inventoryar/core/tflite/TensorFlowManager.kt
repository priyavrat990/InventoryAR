package com.apisod.inventoryar.core.tflite

import android.content.Context
import android.graphics.Bitmap
import com.apisod.inventoryar.core.tflite.model.DetectionResult
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TensorFlowManager(
    private val context: Context
) {

    private var interpreter: Interpreter? = null

    private var labels:
            List<String> = emptyList()

    companion object {

        private const val INPUT_SIZE = 320
        private const val NUM_DETECTIONS = 25
//        private const val IMAGE_MEAN = 127.5f
//        private const val IMAGE_STD = 127.5f
        private const val CONFIDENCE_THRESHOLD = 0.5f
    }

    fun initializeModel() {

        try {

            val model =
                loadModelFile(
                    "detect.tflite"
                )

            labels =
                LabelLoader.loadLabels(
                    context,
                    "labelmap.txt"
                )

            val options =
                Interpreter.Options().apply {

                    setNumThreads(4)
                }

            interpreter =
                Interpreter(model, options)

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    fun detectObjects(
        bitmap: Bitmap
    ): List<DetectionResult> {

        val scaledBitmap =
            Bitmap.createScaledBitmap(
                bitmap,
                INPUT_SIZE,
                INPUT_SIZE,
                true
            )

        val inputBuffer =
            convertBitmapToByteBuffer(
                scaledBitmap
            )

        val outputLocations =
            Array(1) {
                Array(NUM_DETECTIONS) {
                    FloatArray(4)
                }
            }

        val outputClasses =
            Array(1) {
                FloatArray(NUM_DETECTIONS)
            }

        val outputScores =
            Array(1) {
                FloatArray(NUM_DETECTIONS)
            }

        val numDetections =
            FloatArray(1)

        val outputMap =
            mapOf(
                0 to outputLocations,
                1 to outputClasses,
                2 to outputScores,
                3 to numDetections
            )

        interpreter?.runForMultipleInputsOutputs(
            arrayOf(inputBuffer),
            outputMap
        )

        val detectionResults =
            mutableListOf<DetectionResult>()

        val detectedCount =
            numDetections[0].toInt()

        for (i in 0 until detectedCount) {

            val confidence =
                outputScores[0][i]

            if (confidence >=
                CONFIDENCE_THRESHOLD
            ) {

                val classIndex =
                    outputClasses[0][i].toInt()

                val label =
                    labels.getOrElse(classIndex) {
                        "Unknown"
                    }

                val boundingBox =
                    outputLocations[0][i]

                detectionResults.add(
                    DetectionResult(
                        label = label,
                        confidence = confidence,
                        boundingBox = boundingBox
                    )
                )
            }
        }

        return detectionResults
    }

    private fun convertBitmapToByteBuffer(
        bitmap: Bitmap
    ): ByteBuffer {

        val byteBuffer =
            ByteBuffer.allocateDirect(
                INPUT_SIZE * INPUT_SIZE * 3
            )

        byteBuffer.order(
            ByteOrder.nativeOrder()
        )

        val pixels =
            IntArray(
                INPUT_SIZE * INPUT_SIZE
            )

        bitmap.getPixels(
            pixels,
            0,
            bitmap.width,
            0,
            0,
            bitmap.width,
            bitmap.height
        )

        var pixelIndex = 0

        for (i in 0 until INPUT_SIZE) {

            for (j in 0 until INPUT_SIZE) {

                val pixel =
                    pixels[pixelIndex++]

                // RED
                byteBuffer.put(
                    ((pixel shr 16) and 0xFF).toByte()
                )

                // GREEN
                byteBuffer.put(
                    ((pixel shr 8) and 0xFF).toByte()
                )

                // BLUE
                byteBuffer.put(
                    (pixel and 0xFF).toByte()
                )
            }
        }

        return byteBuffer
    }

    private fun loadModelFile(
        modelName: String
    ): MappedByteBuffer {

        val fileDescriptor =
            context.assets.openFd(modelName)

        val inputStream =
            fileDescriptor.createInputStream()

        val fileChannel =
            inputStream.channel

        val startOffset =
            fileDescriptor.startOffset

        val declaredLength =
            fileDescriptor.declaredLength

        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            startOffset,
            declaredLength
        )
    }

    fun close() {

        interpreter?.close()
    }
}