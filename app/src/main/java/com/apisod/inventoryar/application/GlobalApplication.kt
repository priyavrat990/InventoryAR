package com.apisod.inventoryar.application

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.multidex.MultiDex
import com.apisod.inventoryar.constants.Constants
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlobalApplication : Application() {

    companion object {

        private lateinit var instance: GlobalApplication

        lateinit var firebaseFirestore: FirebaseFirestore
            private set

        lateinit var firebaseStorage: FirebaseStorage
            private set

        private var sharedPreferences: SharedPreferences? = null

        fun getInstance(): GlobalApplication {
            return instance
        }

        fun getSharedPrefs(context: Context): SharedPreferences {
            if (sharedPreferences == null) {
                sharedPreferences = context.getSharedPreferences(
                    Constants.MYPREF,
                    Context.MODE_PRIVATE
                )
            }
            return sharedPreferences!!
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        initializeFirebase()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initializeFirebase() {
        FirebaseApp.initializeApp(this)

        firebaseFirestore = FirebaseFirestore.getInstance().apply {
            firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
        }

        firebaseStorage = FirebaseStorage.getInstance()
    }
}