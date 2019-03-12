package com.ahmedadel.robustandroid.core.application.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created at Tito on 3/13/19
 *
 * Dagger Module that provides Application class.
 */

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application
}