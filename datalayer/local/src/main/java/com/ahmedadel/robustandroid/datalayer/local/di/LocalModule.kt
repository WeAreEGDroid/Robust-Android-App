package com.ahmedadel.robustandroid.datalayer.local.di

import android.app.Application
import com.ahmedadel.robustandroid.datalayer.local.DatabaseManager
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/15/19
 *
 * Dagger Module that provides Local stuff like Room Database.
 */

@Module
class LocalModule(private val application: Application) {

    @Provides
    @LocalScope
    fun provideDatabaseManager(): DatabaseManager = DatabaseManager.getInstance(application)

}