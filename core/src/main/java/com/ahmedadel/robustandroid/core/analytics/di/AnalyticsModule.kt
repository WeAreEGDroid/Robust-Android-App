package com.ahmedadel.robustandroid.core.analytics.di

import android.app.Application
import com.ahmedadel.robustandroid.core.analytics.AnalyticsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created at Tito on 3/13/19
 *
 * Dagger Module that provides AnalyticsManager.
 */

@Module
class AnalyticsModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideAnalyticsManager(): AnalyticsManager = AnalyticsManager(application)

}