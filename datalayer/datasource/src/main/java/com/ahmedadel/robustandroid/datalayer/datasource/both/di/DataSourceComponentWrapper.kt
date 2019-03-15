package com.ahmedadel.robustandroid.datalayer.datasource.both.di

import android.app.Application
import com.ahmedadel.robustandroid.core.di.CoreComponent
import com.ahmedadel.robustandroid.core.di.CoreComponentWrapper
import com.ahmedadel.robustandroid.datalayer.datasource.local.di.LocalModule

/**
 * Created at Tito on 3/15/19
 */

open class DataSourceComponentWrapper {

    private lateinit var component: DataSourceComponent

    private fun initializeComponent(coreComponent: CoreComponent) {
        component = DaggerDataSourceComponent.builder()
            .coreComponent(coreComponent)
            .localModule(LocalModule(coreComponent.application()))
            .build()
    }

    companion object {

        private var wrapper: DataSourceComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): DataSourceComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = DataSourceComponentWrapper()
                    wrapper!!.initializeComponent(CoreComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}