package com.apisod.inventoryar.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

open class CentralizedActivity : AppCompatActivity() {

    protected lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun getUpdatedFirebaseAuth(): FirebaseAuth {
        return firebaseAuth
    }
}