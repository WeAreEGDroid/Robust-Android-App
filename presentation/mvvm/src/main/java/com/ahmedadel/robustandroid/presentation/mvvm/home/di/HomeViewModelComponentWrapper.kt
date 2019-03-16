package com.ahmedadel.robustandroid.presentation.mvvm.home.di

import android.app.Application
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponent
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponentWrapper
import com.ahmedadel.robustandroid.feature.movie.di.MovieModule
import com.ahmedadel.robustandroid.feature.person.di.PersonModule
import com.ahmedadel.robustandroid.feature.tv.di.TVModule

/**
 * Created at Tito on 3/16/19
 */

class HomeViewModelComponentWrapper {

    private lateinit var component: HomeViewModelComponent

    private fun initializeComponent(dataSourceComponent: DataSourceComponent) {
        val homeUseCasesComponent = DaggerHomeUseCasesComponent.builder()
            .movieModule(
                MovieModule(
                    dataSourceComponent.databaseManager().movieDao(),
                    dataSourceComponent.apiService()
                )
            )
            .personModule(
                PersonModule(
                    dataSourceComponent.databaseManager().personDao(),
                    dataSourceComponent.apiService()
                )
            )
            .tVModule(
                TVModule(
                    dataSourceComponent.databaseManager().tvDao(),
                    dataSourceComponent.apiService()
                )
            )
            .dataSourceComponent(dataSourceComponent)
            .build()

        component = DaggerHomeViewModelComponent.builder()
            .homeUseCasesComponent(homeUseCasesComponent)
            .build()
    }

    companion object {

        private var wrapper: HomeViewModelComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): HomeViewModelComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = HomeViewModelComponentWrapper()
                    wrapper!!.initializeComponent(DataSourceComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}