package com.ahmedadel.robustandroid.core.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created at Tito on 3/13/19
 *
 * Wrapper class for dealing with SharedPreferences in an easy way.
 */

@Suppress("unused")
@Singleton
class SharedPrefWrapper @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun deleteKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun getString(key: String, defValue: String = ""): String {
        return sharedPreferences.getString(key, defValue) ?: ""
    }

    fun getStringOrNull(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defValue: Int = -1): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val SHARED_PREFERENCE_KEY = "ROBUST_ANDROID_APP"
    }

}
