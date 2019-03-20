package com.ahmedadel.robustandroid.tvdetails.di

import android.app.Application
import com.ahmedadel.robustandroid.presentation.mvi.tv.di.TVDetailsComponent
import com.ahmedadel.robustandroid.presentation.mvi.tv.di.TVDetailsComponentWrapper
import com.ahmedadel.robustandroid.tvdetails.TVDetailsActivity

/**
 * Created at Tito on 3/20/19
 */

class TVDetailsActivityComponentWrapper {

    private lateinit var component: TVDetailsActivityComponent

    private fun initializeComponent(tvDetailsComponent: TVDetailsComponent) {
        component = DaggerTVDetailsActivityComponent.builder()
            .tVDetailsComponent(tvDetailsComponent)
            .build()
    }

    companion object {

        private var wrapper: TVDetailsActivityComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): TVDetailsActivityComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = TVDetailsActivityComponentWrapper()
                    wrapper!!.initializeComponent(TVDetailsComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun buildComponent(tvDetailsActivity: TVDetailsActivity) {
            getInstance(tvDetailsActivity.application).component.inject(tvDetailsActivity)
        }

    }

}