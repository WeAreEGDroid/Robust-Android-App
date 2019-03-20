package com.ahmedadel.robustandroid.presentation.mvi.movie.di

import android.app.Application
import com.ahmedadel.robustandroid.feature.movie.di.MovieComponent
import com.ahmedadel.robustandroid.feature.movie.di.MovieComponentWrapper

/**
 * Created at Tito on 3/20/19
 */

open class MovieDetailsComponentWrapper {

    private lateinit var component: MovieDetailsComponent

    private fun initializeComponent(movieComponent: MovieComponent) {
        component = DaggerMovieDetailsComponent.builder()
            .movieComponent(movieComponent)
            .build()
    }

    companion object {

        private var wrapper: MovieDetailsComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): MovieDetailsComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = MovieDetailsComponentWrapper()
                    wrapper!!.initializeComponent(MovieComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}