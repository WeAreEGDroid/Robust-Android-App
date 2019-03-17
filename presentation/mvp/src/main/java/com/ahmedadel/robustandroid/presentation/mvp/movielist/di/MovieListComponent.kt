package com.ahmedadel.robustandroid.presentation.mvp.movielist.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProviderModule
import com.ahmedadel.robustandroid.feature.movie.di.MovieComponent
import com.ahmedadel.robustandroid.presentation.mvp.movielist.MovieListContract
import com.ahmedadel.robustandroid.presentation.mvp.movielist.MovieListPresenter
import dagger.Component

/**
 * Created at Tito on 3/17/19
 */

@PresentationScope
@Component(
    modules = [
        MovieListModule::class,
        BaseSchedulerProviderModule::class
    ],
    dependencies = [
        MovieComponent::class
    ]
)
interface MovieListComponent {

    fun movieListPresenter(): MovieListPresenter

    fun movieListModel() : MovieListContract.Model

}
