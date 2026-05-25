//package com.apisod.inventoryar.application;
//
//import android.app.Application;
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import androidx.multidex.MultiDex;
//
//import com.apisod.inventoryar.constants.Constants;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreSettings;
//import com.google.firebase.storage.FirebaseStorage;
//
//import dagger.hilt.android.HiltAndroidApp;
//
//@HiltAndroidApp
//public class GlobalApplication extends Application {
//
//    private static GlobalApplication globalApplication;
//    private static FirebaseFirestore firebaseFirestore;
//    private static FirebaseStorage storage;
//    private static SharedPreferences mPrefs;
//
//    public static GlobalApplication getInstance() {
////        if (globalApplication == null) {
////            System.out.println("GlobalApplication new getInstance");
////            globalApplication = new GlobalApplication();
////        }
//        return globalApplication;
//    }
//
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        globalApplication = this;
//
//        try {
//            FirebaseApp.initializeApp(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        initFirebaseFirestore();
//    }
//
//    public static FirebaseFirestore getFirebaseFirestore() {
//        return firebaseFirestore;
//    }
//
//    private void initFirebaseFirestore() {
//        try {
//            if (firebaseFirestore == null) {
//                firebaseFirestore = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setPersistenceEnabled(true)
//                        .build();
//                firebaseFirestore.setFirestoreSettings(settings);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static FirebaseStorage getFirebaseStorage() {
//        if (storage == null)
//            storage = FirebaseStorage.getInstance();
//        return storage;
//    }
//
//    public static SharedPreferences getSharedPrefs(Context context) {
//        if (mPrefs == null)
//            mPrefs = context.getSharedPreferences(Constants.MYPREF, Context.MODE_PRIVATE);
//        return mPrefs;
//    }
//}
