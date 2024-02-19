package com.zabs.zabstest.applicationClass

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.zabs.zabstest.interfaces.FirebaseInitializedCallback

class AppAplication : Application() {

    companion object {
        private lateinit var instance: AppAplication

        fun getMyContext(): Context {
            if (!::instance.isInitialized) {
                throw RuntimeException("AppApplication instance is not initialized yet.")
            }
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeFirebase(object : FirebaseInitializedCallback {
            override fun onFirebaseInitialized() {
                // Firebase initialized callback
            }
        })
    }
    private fun initializeFirebase(callback: FirebaseInitializedCallback) {
        FirebaseApp.initializeApp(this)
        // Additional Firebase initialization code if needed

        // Notify callback that Firebase initialization is complete
        callback.onFirebaseInitialized()
    }
}