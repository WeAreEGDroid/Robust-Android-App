package com.ahmedadel.robustandroid.core.sharedpreferences.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ahmedadel.robustandroid.core.sharedpreferences.SharedPrefWrapper.Companion.SHARED_PREFERENCE_KEY
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created at Tito on 3/13/19
 *
 * Dagger module that provides SharedPreferences Class
 */

@Module
class SharedPreferencesModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)

}