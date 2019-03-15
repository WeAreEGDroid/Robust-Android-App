package com.ahmedadel.robustandroid.feature.movie.di

import android.app.Application
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponent
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponentWrapper

/**
 * Created at Tito on 3/15/19
 */

open class MovieComponentWrapper {

    private lateinit var component: MovieComponent

    private fun initializeComponent(dataSourceComponent: DataSourceComponent) {
        component = DaggerMovieComponent.builder()
            .movieModule(
                MovieModule(
                    dataSourceComponent.databaseManager().movieDao(),
                    dataSourceComponent.apiService()
                )
            )
            .dataSourceComponent(dataSourceComponent)
            .build()
    }

    companion object {

        private var wrapper: MovieComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): MovieComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = MovieComponentWrapper()
                    wrapper!!.initializeComponent(DataSourceComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}