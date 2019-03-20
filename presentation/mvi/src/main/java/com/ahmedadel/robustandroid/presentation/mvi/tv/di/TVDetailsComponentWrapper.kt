package com.ahmedadel.robustandroid.presentation.mvi.tv.di

import android.app.Application
import com.ahmedadel.robustandroid.feature.tv.di.TVComponent
import com.ahmedadel.robustandroid.feature.tv.di.TVComponentWrapper

/**
 * Created at Tito on 3/20/19
 */

open class TVDetailsComponentWrapper {

    private lateinit var component: TVDetailsComponent

    private fun initializeComponent(tvComponent: TVComponent) {
        component = DaggerTVDetailsComponent.builder()
            .tVComponent(tvComponent)
            .build()
    }

    companion object {

        private var wrapper: TVDetailsComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): TVDetailsComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = TVDetailsComponentWrapper()
                    wrapper!!.initializeComponent(TVComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}