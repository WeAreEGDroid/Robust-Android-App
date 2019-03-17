package com.ahmedadel.robustandroid.movielist.di

import android.app.Application
import com.ahmedadel.robustandroid.movielist.MovieListActivity
import com.ahmedadel.robustandroid.presentation.mvp.movielist.di.MovieListComponent
import com.ahmedadel.robustandroid.presentation.mvp.movielist.di.MovieListComponentWrapper

/**
 * Created at Tito on 3/17/19
 */

class MovieListActivityComponentWrapper {

    private lateinit var component: MovieListActivityComponent

    private fun initializeComponent(movieListComponent: MovieListComponent) {
        component = DaggerMovieListActivityComponent.builder()
            .movieListComponent(movieListComponent)
            .build()
    }

    companion object {

        private var wrapper: MovieListActivityComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): MovieListActivityComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = MovieListActivityComponentWrapper()
                    wrapper!!.initializeComponent(MovieListComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun buildComponent(movieListActivity: MovieListActivity) {
            getInstance(movieListActivity.application).component.inject(movieListActivity)
        }

    }

}