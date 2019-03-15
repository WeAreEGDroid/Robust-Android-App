package com.ahmedadel.robustandroid.datalayer.datasource.local.di

import com.ahmedadel.robustandroid.core.di.CoreComponent
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceScope
import com.ahmedadel.robustandroid.datalayer.local.DatabaseManager
import dagger.Component

/**
 * Created at Tito on 3/15/19
 *
 * Dagger Component that will provide and implement the initialization of Local Module.
 */

@DataSourceScope
@Component(
    modules = [
        LocalModule::class
    ],
    dependencies = [
        CoreComponent::class
    ]
)
interface LocalComponent {

    fun databaseManager(): DatabaseManager
}