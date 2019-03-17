package com.ahmedadel.robustandroid.presentation.mvp.tvlist.di

import android.app.Application
import com.ahmedadel.robustandroid.feature.tv.di.TVComponent
import com.ahmedadel.robustandroid.feature.tv.di.TVComponentWrapper

/**
 * Created at Tito on 3/17/19
 */

open class TVListComponentWrapper {

    private lateinit var component: TVListComponent

    private fun initializeComponent(tvComponent: TVComponent) {
        component = DaggerTVListComponent.builder()
            .tVComponent(tvComponent)
            .build()
    }

    companion object {

        private var wrapper: TVListComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): TVListComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = TVListComponentWrapper()
                    wrapper!!.initializeComponent(TVComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}