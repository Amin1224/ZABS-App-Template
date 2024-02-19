package com.zabs.zabstest.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseApp
import com.zabs.zabstest.R
import com.zabs.zabstest.databinding.ActivitySplashScreenBinding
import com.zabs.zabstest.interfaces.FirebaseInitializedCallback
import com.zabs.zabstest.remoteConfig.RemoteCOnfig
import com.zabs.zabstest.sharedPref.AppPreferences
import com.zurmati.zeem.ads.managers.AdsManager

class SplashScreen : AppCompatActivity(), FirebaseInitializedCallback {
    private val binding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requiredData()
        // Initialize AdsManager
        AdsManager.initAdManager(this)

        // Set status bar color for devices with Lollipop and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        }

        // Delayed start of the MainActivity after 3000 milliseconds
        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashScreen,MainActivity::class.java))
            finish() // Optional: finish the Splash activity to prevent going back
        }, 3000)
    }

    private fun requiredData() {
        // Make sure AppPreferences.getInstance() is not called before AppApplication is initialized
        AdsManager.adData.bannerId = getString(R.string.AdmobBannerId)
        AdsManager.adData.interstitialId = getString(R.string.AdmobInterstitialId)
        AdsManager.adData.nativeId = getString(R.string.AdmobNativeId)

        // Ensure AppPreferences.getInstance() is called after AppApplication is initialized
        AdsManager.adData.clickCapping = AppPreferences.getInstance().getClickCapping()
        AdsManager.adData.BannerRequests = AppPreferences.getInstance().getBannar()
        AdsManager.adData.interstitialRequests = AppPreferences.getInstance().getIntersial()
        AdsManager.adData.nativeRequest = AppPreferences.getInstance().getNative()
    }

    override fun onFirebaseInitialized() {
        RemoteCOnfig.fetchRemoteConfig(this)
    }
}