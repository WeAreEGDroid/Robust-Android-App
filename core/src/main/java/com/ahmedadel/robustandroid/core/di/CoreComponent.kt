package com.ahmedadel.robustandroid.core.di

import android.app.Application
import android.content.SharedPreferences
import com.ahmedadel.robustandroid.core.analytics.AnalyticsManager
import com.ahmedadel.robustandroid.core.analytics.di.AnalyticsModule
import com.ahmedadel.robustandroid.core.application.di.ApplicationModule
import com.ahmedadel.robustandroid.core.sharedpreferences.di.SharedPreferencesModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created at Tito on 3/13/19
 *
 * This component makes dagger implements the initialization of mentioned modules.
 */

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        SharedPreferencesModule::class,
        AnalyticsModule::class
    ]
)
interface CoreComponent {

    fun application(): Application

    fun sharedPreferences(): SharedPreferences

    fun analyticsManager(): AnalyticsManager

}