package com.ahmedadel.robustandroid.datalayer.datasource.both.di

import com.ahmedadel.robustandroid.datalayer.datasource.local.di.LocalModule
import com.ahmedadel.robustandroid.datalayer.datasource.remote.di.RemoteModule

import com.ahmedadel.robustandroid.core.di.CoreComponent

import com.ahmedadel.robustandroid.datalayer.local.DatabaseManager
import com.ahmedadel.robustandroid.datalayer.remote.ApiService

import dagger.Component

/**
 * Created at Tito on 3/15/19
 */

@DataSourceScope
@Component(
    modules = [
        RemoteModule::class,
        LocalModule::class
    ],
    dependencies = [
        CoreComponent::class
    ]
)
interface DataSourceComponent {

    fun apiService(): ApiService

    fun databaseManager(): DatabaseManager

}