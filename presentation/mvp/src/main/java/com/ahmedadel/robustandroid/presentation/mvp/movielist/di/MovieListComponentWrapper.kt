package com.ahmedadel.robustandroid.presentation.mvp.movielist.di

import android.app.Application
import com.ahmedadel.robustandroid.feature.movie.di.MovieComponent
import com.ahmedadel.robustandroid.feature.movie.di.MovieComponentWrapper

/**
 * Created at Tito on 3/17/19
 */

open class MovieListComponentWrapper {

    private lateinit var component: MovieListComponent

    private fun initializeComponent(movieComponent: MovieComponent) {
        component = DaggerMovieListComponent.builder()
            .movieComponent(movieComponent)
            .build()
    }

    companion object {

        private var wrapper: MovieListComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): MovieListComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = MovieListComponentWrapper()
                    wrapper!!.initializeComponent(MovieComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}