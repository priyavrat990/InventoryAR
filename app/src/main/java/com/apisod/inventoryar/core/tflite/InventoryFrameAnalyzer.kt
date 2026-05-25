package com.apisod.inventoryar.core.tflite

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class InventoryFrameAnalyzer(
    private val context: Context
) : ImageAnalysis.Analyzer {

    private val tensorFlowManager =
        TensorFlowManager(context)

    companion object {

        private const val FRAME_SKIP_COUNT = 5

        private const val INFERENCE_INTERVAL_MS = 300L
    }

    private var frameCounter = 0

    private var lastInferenceTime = 0L

    init {

        tensorFlowManager.initializeModel()
    }

    override fun analyze(
        image: ImageProxy
    ) {

        try {

            frameCounter++

            // Skip frames
            if (
                frameCounter %
                FRAME_SKIP_COUNT != 0
            ) {

                image.close()
                return
            }

            val currentTime =
                SystemClock.elapsedRealtime()

            // Throttle inference
            if (
                currentTime -
                lastInferenceTime
                < INFERENCE_INTERVAL_MS
            ) {

                image.close()
                return
            }

            lastInferenceTime =
                currentTime

            val bitmap =
                ImageUtils.imageProxyToBitmap(
                    image
                )

            bitmap?.let {

                val detections =
                    tensorFlowManager
                        .detectObjects(it)

                Log.d(
                    "TFLITE_DETECTION",
                    "Detection Count: ${detections.size}"
                )

                detections.forEach { detection ->

                    Log.d(
                        "TFLITE_DETECTION",
                        "Detected: ${detection.label} " +
                                "Confidence: ${detection.confidence}"
                    )
                }
            }

        } catch (e: Exception) {

            e.printStackTrace()

        } finally {

            image.close()
        }
    }
}