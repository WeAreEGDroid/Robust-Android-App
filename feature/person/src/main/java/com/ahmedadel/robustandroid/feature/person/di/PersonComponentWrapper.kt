package com.ahmedadel.robustandroid.feature.person.di

import android.app.Application
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponent
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponentWrapper

/**
 * Created at Tito on 3/15/19
 */

open class PersonComponentWrapper {

    private lateinit var component: PersonComponent

    private fun initializeComponent(dataSourceComponent: DataSourceComponent) {
        component = DaggerPersonComponent.builder()
            .personModule(
                PersonModule(
                    dataSourceComponent.databaseManager().personDao(),
                    dataSourceComponent.apiService()
                )
            )
            .dataSourceComponent(dataSourceComponent)
            .build()
    }

    companion object {

        private var wrapper: PersonComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): PersonComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = PersonComponentWrapper()
                    wrapper!!.initializeComponent(DataSourceComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}