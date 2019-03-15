package com.ahmedadel.robustandroid.datalayer.datasource.remote.di

import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceScope
import com.ahmedadel.robustandroid.datalayer.remote.ApiService
import dagger.Component

/**
 * Created at Tito on 3/15/19
 *
 * Dagger Component that will provide and implement the initialization of Remote Module.
 */

@DataSourceScope
@Component(
    modules = [
        RemoteModule::class
    ]
)
interface RemoteComponent {

    fun apiService(): ApiService

}