package com.ahmedadel.robustandroid.core.analytics

import android.app.Application
import android.util.Log

/**
 * Created at Tito on 3/13/19
 *
 * Dummy class as a simulation for analytics part.
 */

@Suppress("unused")
class AnalyticsManager(private val application: Application) {

    fun logScreenView(screenName: String) {
        Log.d("Teamwork Analytics", "Logged screen name: $screenName")
    }
}