package com.zabs.zabstest.remoteConfig

import android.app.Activity
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.zabs.zabstest.sharedPref.AppPreferences

class RemoteCOnfig {
    companion object {

        var isCheck = false
        fun fetchRemoteConfig(splashScreen: Activity) {
            if (isCheck)
                return

            // Enable Firebase Remote Config
            val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0L) // Minimum fetch interval in seconds
                .build()

            val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            firebaseRemoteConfig.setConfigSettingsAsync(configSettings)


            // Fetch remote config values
            Firebase.remoteConfig.fetchAndActivate()
                .addOnCompleteListener(splashScreen) { task ->
                    if (task.isSuccessful) {
                        isCheck = true
                        val updated = task.result

                        Log.d("RemoteConfig", "Config params updated: $updated")

                        // Retrieve the value for Click_Capping
                        val clickCappingValue = firebaseRemoteConfig.getString("clickCapping")
                        val bannar = firebaseRemoteConfig.getString("banner")
                        val intersial = firebaseRemoteConfig.getString("intersial")
                        val native = firebaseRemoteConfig.getString("native")
                        saveToSharedPreferences(clickCappingValue, bannar, intersial, native)

                        Log.i("RemoteConfig", "intersial: $intersial")
                        Log.i("RemoteConfig", "banner: $bannar")
                        Log.i("RemoteConfig", "native: $native")
                        Log.i("RemoteConfig", "clickCappingValue: $clickCappingValue")


                    } else {
                        Log.e("RemoteConfig", "Fetch failed")
                    }


                }
        }

        private fun saveToSharedPreferences(
            clickCappingValue: String,
            bannar: String,
            intersial: String,
            native: String
        ) {
            if (clickCappingValue.isNotEmpty() && bannar.isNotEmpty() && intersial.isNotEmpty() && native.isNotEmpty()){

                AppPreferences.getInstance().setClickCapping(clickCappingValue.toInt())
                AppPreferences.getInstance().setBannar(bannar.toInt())
                AppPreferences.getInstance().setIntersial(intersial.toInt())
                AppPreferences.getInstance().setNative(native.toInt())
                AppPreferences.getInstance().applyChanges()
            }

        }

    }
}