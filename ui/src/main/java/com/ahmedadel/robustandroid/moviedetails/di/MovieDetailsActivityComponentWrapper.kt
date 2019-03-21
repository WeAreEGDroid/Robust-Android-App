package com.ahmedadel.robustandroid.moviedetails.di

import com.ahmedadel.robustandroid.feature.movie.di.MovieComponentWrapper
import com.ahmedadel.robustandroid.moviedetails.MovieDetailsActivity
import com.ahmedadel.robustandroid.presentation.mvi.movie.di.DaggerMovieDetailsComponent

/**
 * Created at Tito on 3/20/19
 */

object MovieDetailsActivityComponentWrapper {

    fun buildComponent(movieDetailsActivity: MovieDetailsActivity) {
        DaggerMovieDetailsActivityComponent
            .builder()
            .movieDetailsComponent(
                DaggerMovieDetailsComponent
                    .builder()
                    .movieComponent(MovieComponentWrapper.getComponent(movieDetailsActivity.application))
                    .build()
            )
            .build()
            .inject(movieDetailsActivity)
    }

}