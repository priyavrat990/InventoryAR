package com.apisod.inventoryar.core.tflite

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream

object ImageUtils {

    fun imageProxyToBitmap(
        imageProxy: ImageProxy
    ): Bitmap? {

        return try {

            val nv21 =
                yuv420888ToNv21(imageProxy)

            val yuvImage =
                YuvImage(
                    nv21,
                    ImageFormat.NV21,
                    imageProxy.width,
                    imageProxy.height,
                    null
                )

            val out =
                ByteArrayOutputStream()

            yuvImage.compressToJpeg(
                Rect(
                    0,
                    0,
                    imageProxy.width,
                    imageProxy.height
                ),
                100,
                out
            )

            val imageBytes =
                out.toByteArray()

            val bitmap =
                BitmapFactory.decodeByteArray(
                    imageBytes,
                    0,
                    imageBytes.size
                )

            rotateBitmap(
                bitmap,
                imageProxy.imageInfo.rotationDegrees
                    .toFloat()
            )

        } catch (e: Exception) {

            e.printStackTrace()

            null
        }
    }

    private fun yuv420888ToNv21(
        image: ImageProxy
    ): ByteArray {

        val yBuffer =
            image.planes[0].buffer

        val uBuffer =
            image.planes[1].buffer

        val vBuffer =
            image.planes[2].buffer

        val ySize =
            yBuffer.remaining()

        val uSize =
            uBuffer.remaining()

        val vSize =
            vBuffer.remaining()

        val nv21 =
            ByteArray(ySize + uSize + vSize)

        yBuffer.get(
            nv21,
            0,
            ySize
        )

        vBuffer.get(
            nv21,
            ySize,
            vSize
        )

        uBuffer.get(
            nv21,
            ySize + vSize,
            uSize
        )

        return nv21
    }

    private fun rotateBitmap(
        bitmap: Bitmap,
        rotationDegrees: Float
    ): Bitmap {

        val matrix = Matrix()

        matrix.postRotate(rotationDegrees)

        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }
}