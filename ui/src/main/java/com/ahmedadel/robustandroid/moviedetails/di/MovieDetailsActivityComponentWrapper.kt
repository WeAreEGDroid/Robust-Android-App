package com.ahmedadel.robustandroid.moviedetails.di

import android.app.Application
import com.ahmedadel.robustandroid.moviedetails.MovieDetailsActivity
import com.ahmedadel.robustandroid.presentation.mvi.movie.di.MovieDetailsComponent
import com.ahmedadel.robustandroid.presentation.mvi.movie.di.MovieDetailsComponentWrapper

/**
 * Created at Tito on 3/20/19
 */

class MovieDetailsActivityComponentWrapper {

    private lateinit var component: MovieDetailsActivityComponent

    private fun initializeComponent(movieDetailsComponent: MovieDetailsComponent) {
        component = DaggerMovieDetailsActivityComponent.builder()
            .movieDetailsComponent(movieDetailsComponent)
            .build()
    }

    companion object {

        private var wrapper: MovieDetailsActivityComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): MovieDetailsActivityComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = MovieDetailsActivityComponentWrapper()
                    wrapper!!.initializeComponent(MovieDetailsComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun buildComponent(movieDetailsActivity: MovieDetailsActivity) {
            getInstance(movieDetailsActivity.application).component.inject(movieDetailsActivity)
        }

    }

}