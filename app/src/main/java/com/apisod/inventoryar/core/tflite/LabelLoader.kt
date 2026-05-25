package com.apisod.inventoryar.core.tflite

import android.content.Context

object LabelLoader {

    fun loadLabels(
        context: Context,
        fileName: String
    ): List<String> {

        return context.assets
            .open(fileName)
            .bufferedReader()
            .use { reader ->

                reader.readLines()
            }
    }
}