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
class GlobalApplication :
    Application() {

    companion object {

        private lateinit var instance:
                GlobalApplication

        private lateinit var firebaseFirestore:
                FirebaseFirestore

        private lateinit var firebaseStorage:
                FirebaseStorage

        private var sharedPreferences:
                SharedPreferences? = null

        fun getInstance():
                GlobalApplication {

            return instance
        }

        fun provideFirebaseFirestore():
                FirebaseFirestore {

            return firebaseFirestore
        }

        fun provideFirebaseStorage():
                FirebaseStorage {

            return firebaseStorage
        }

        fun getSharedPrefs(
            context: Context
        ): SharedPreferences {

            if (sharedPreferences == null) {

                sharedPreferences =
                    context.getSharedPreferences(
                        Constants.MYPREF,
                        Context.MODE_PRIVATE
                    )
            }

            return sharedPreferences!!
        }
    }

    override fun attachBaseContext(
        base: Context
    ) {

        super.attachBaseContext(base)

        MultiDex.install(this)
    }

    override fun onCreate() {

        super.onCreate()

        instance = this

        initializeFirebase()
    }

    private fun initializeFirebase() {

        try {

            FirebaseApp.initializeApp(
                this
            )

            firebaseFirestore =
                FirebaseFirestore
                    .getInstance()

            val settings =
                FirebaseFirestoreSettings
                    .Builder()
                    .setPersistenceEnabled(true)
                    .build()

            firebaseFirestore
                .firestoreSettings =
                settings

            firebaseStorage =
                FirebaseStorage
                    .getInstance()

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }
}