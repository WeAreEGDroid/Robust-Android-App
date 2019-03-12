package com.ahmedadel.robustandroid.core.di

import android.app.Application
import com.ahmedadel.robustandroid.core.analytics.di.AnalyticsModule
import com.ahmedadel.robustandroid.core.application.di.ApplicationModule
import com.ahmedadel.robustandroid.core.sharedpreferences.di.SharedPreferencesModule

/**
 * Created at Tito on 3/13/19
 *
 * Wrapper core component class that will be used to initialize the desired modules from core android module
 */

open class CoreComponentWrapper private constructor() {

    private lateinit var component: CoreComponent

    private fun initializeComponent(application: Application): CoreComponent? {
        component = DaggerCoreComponent.builder()
            .applicationModule(ApplicationModule(application))
            .sharedPreferencesModule(SharedPreferencesModule(application))
            .analyticsModule(AnalyticsModule(application))
            .build()
        return component
    }

    companion object {

        private var wrapper: CoreComponentWrapper? = null

        fun getInstance(application: Application): CoreComponentWrapper {
            if (wrapper == null) {
                synchronized(CoreComponentWrapper::class.java) {
                    if (wrapper == null) {
                        wrapper = CoreComponentWrapper()
                        wrapper!!.initializeComponent(application)
                    }
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) =
            getInstance(application).component
    }
}
