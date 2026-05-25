package com.apisod.inventoryar

import android.graphics.Color
import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.apisod.inventoryar.common.CentralizedActivity

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : CentralizedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupSystemBars()
        setContentView(R.layout.activity_main)
    }

    private fun setupSystemBars() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.statusBarColor = Color.BLACK
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = false
    }
}
