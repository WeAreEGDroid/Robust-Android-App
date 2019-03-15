package com.ahmedadel.robustandroid.datalayer.datasource.local.di

import android.app.Application
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceScope
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
    @DataSourceScope
    fun provideDatabaseManager(): DatabaseManager = DatabaseManager.getInstance(application)

}