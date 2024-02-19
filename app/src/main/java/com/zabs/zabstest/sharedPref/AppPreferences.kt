package com.zabs.zabstest.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.zabs.zabstest.applicationClass.AppAplication

class AppPreferences private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val PREFS_NAME = "MyPrefs"

        // Define your keys here
        const val KEY_CLICK_CAPPING = "Click_Capping"
        const val KEY_BANNAR = "Bannar"
        const val KEY_INTERSIAL = "Intersial"
        const val KEY_NATIVE = "Native"

        @Volatile
        private var instance: AppPreferences? = null

        fun getInstance(): AppPreferences =
            instance ?: synchronized(this) {
                instance ?: AppPreferences(AppAplication.getMyContext()).also { instance = it }
            }
    }

    // Define methods to get and set values
    fun getClickCapping(): Int = sharedPreferences.getInt(KEY_CLICK_CAPPING, 3)
    fun setClickCapping(value: Int) = editor.putInt(KEY_CLICK_CAPPING, value).apply()

    fun getBannar(): Int = sharedPreferences.getInt(KEY_BANNAR, 3)
    fun setBannar(value: Int) = editor.putInt(KEY_BANNAR, value).apply()

    fun getIntersial(): Int = sharedPreferences.getInt(KEY_INTERSIAL, 3)
    fun setIntersial(value: Int) = editor.putInt(KEY_INTERSIAL, value).apply()

    fun getNative(): Int = sharedPreferences.getInt(KEY_NATIVE, 3)
    fun setNative(value: Int) = editor.putInt(KEY_NATIVE, value).apply()

    // Add more methods as needed

    // Commit the changes
    fun applyChanges() {
        editor.apply()
    }
}