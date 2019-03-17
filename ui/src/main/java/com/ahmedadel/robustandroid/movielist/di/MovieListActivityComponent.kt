package com.ahmedadel.robustandroid.movielist.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.movielist.MovieListActivity
import com.ahmedadel.robustandroid.presentation.mvp.movielist.di.MovieListComponent
import dagger.Component

/**
 * Created at Tito on 3/17/19
 */

@ActivityScope
@Component(
    modules = [
        MovieListActivityModule::class
    ],
    dependencies = [
        MovieListComponent::class
    ]
)
interface MovieListActivityComponent {

    fun inject(movieListActivity: MovieListActivity)

}