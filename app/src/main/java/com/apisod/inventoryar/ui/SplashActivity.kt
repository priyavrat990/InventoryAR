package com.apisod.inventoryar.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.apisod.inventoryar.MainActivity
import com.apisod.inventoryar.R
import com.apisod.inventoryar.common.CentralizedActivity

class SplashActivity : CentralizedActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Hide the action bar for a true splash screen experience
        supportActionBar?.hide()

        // 2-second delay (2000 milliseconds)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Prevent going back to the splash screen
        }, 2000)
    }
}
