package com.ahmedadel.robustandroid.home.di

import android.app.Application
import com.ahmedadel.robustandroid.home.HomeActivity
import com.ahmedadel.robustandroid.presentation.mvvm.home.di.HomeViewModelComponent
import com.ahmedadel.robustandroid.presentation.mvvm.home.di.HomeViewModelComponentWrapper

/**
 * Created at Tito on 3/16/19
 */

class HomeActivityComponentWrapper {

    private lateinit var component: HomeActivityComponent

    private fun initializeComponent(homeViewModelComponent: HomeViewModelComponent) {
        component = DaggerHomeActivityComponent.builder()
            .homeViewModelComponent(homeViewModelComponent)
            .build()
    }

    companion object {

        private var wrapper: HomeActivityComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): HomeActivityComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = HomeActivityComponentWrapper()
                    wrapper!!.initializeComponent(HomeViewModelComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun buildComponent(homeActivity: HomeActivity) {
            getInstance(homeActivity.application).component.inject(homeActivity)
        }

    }

}