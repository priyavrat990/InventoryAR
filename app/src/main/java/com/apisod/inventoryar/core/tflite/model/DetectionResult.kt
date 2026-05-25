package com.apisod.inventoryar.core.tflite.model

data class DetectionResult(

    val label: String,

    val confidence: Float,

    val boundingBox: FloatArray
)