package com.ahmedadel.robustandroid.moviedetails.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.moviedetails.MovieDetailsActivity
import com.ahmedadel.robustandroid.presentation.mvi.movie.di.MovieDetailsComponent
import dagger.Component

/**
 * Created at Tito on 3/20/19
 */
@ActivityScope
@Component(
    dependencies = [
        MovieDetailsComponent::class
    ]
)
interface MovieDetailsActivityComponent {

    fun inject(movieDetailsActivity: MovieDetailsActivity)

}