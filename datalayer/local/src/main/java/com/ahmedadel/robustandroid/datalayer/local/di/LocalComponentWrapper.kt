package com.ahmedadel.robustandroid.datalayer.local.di

import android.app.Application
import com.ahmedadel.robustandroid.core.di.CoreComponent
import com.ahmedadel.robustandroid.core.di.CoreComponentWrapper

/**
 * Created at Tito on 3/15/19
 *
 * Wrapper class with singleton behaviour to initialize Dagger Local Component.
 */

open class LocalComponentWrapper {

    private lateinit var component: LocalComponent

    private fun initializeComponent(coreComponent: CoreComponent) {
        component = DaggerLocalComponent.builder()
            .coreComponent(coreComponent)
            .localModule(LocalModule(coreComponent.application()))
            .build()
    }

    companion object {

        private var wrapper: LocalComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): LocalComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = LocalComponentWrapper()
                    wrapper!!.initializeComponent(CoreComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component
    }

}