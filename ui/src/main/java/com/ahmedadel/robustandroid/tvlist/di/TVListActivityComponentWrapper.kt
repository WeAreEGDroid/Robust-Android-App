package com.ahmedadel.robustandroid.tvlist.di

import android.app.Application
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.di.TVListComponent
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.di.TVListComponentWrapper
import com.ahmedadel.robustandroid.tvlist.TVListActivity

/**
 * Created at Tito on 3/18/19
 */

class TVListActivityComponentWrapper {

    private lateinit var component: TVListActivityComponent

    private fun initializeComponent(tvListComponent: TVListComponent) {
        component = DaggerTVListActivityComponent.builder()
            .tVListComponent(tvListComponent)
            .build()
    }

    companion object {

        private var wrapper: TVListActivityComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): TVListActivityComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = TVListActivityComponentWrapper()
                    wrapper!!.initializeComponent(TVListComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun buildComponent(tvListActivity: TVListActivity) {
            getInstance(tvListActivity.application).component.inject(tvListActivity)
        }

    }

}