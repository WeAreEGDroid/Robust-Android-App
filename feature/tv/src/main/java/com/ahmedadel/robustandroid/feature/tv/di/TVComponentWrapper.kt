package com.ahmedadel.robustandroid.feature.tv.di

import android.app.Application
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponent
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponentWrapper

/**
 * Created at Tito on 3/17/19
 */

open class TVComponentWrapper {

    private lateinit var component: TVComponent

    private fun initializeComponent(dataSourceComponent: DataSourceComponent) {
        component = DaggerTVComponent.builder()
            .tVModule(
                TVModule(
                    dataSourceComponent.databaseManager().tvDao(),
                    dataSourceComponent.apiService()
                )
            )
            .dataSourceComponent(dataSourceComponent)
            .build()
    }

    companion object {

        private var wrapper: TVComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): TVComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = TVComponentWrapper()
                    wrapper!!.initializeComponent(DataSourceComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}