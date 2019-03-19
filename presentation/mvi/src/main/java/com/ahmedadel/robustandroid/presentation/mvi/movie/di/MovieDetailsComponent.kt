package com.ahmedadel.robustandroid.presentation.mvi.movie.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProviderModule
import com.ahmedadel.robustandroid.feature.movie.di.MovieComponent
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsProcessor
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsViewModel
import dagger.Component

/**
 * Created at Tito on 3/19/19
 */

@PresentationScope
@Component(
    modules = [
        MovieDetailsModule::class,
        BaseSchedulerProviderModule::class
    ],
    dependencies = [
        MovieComponent::class
    ]
)
interface MovieDetailsComponent {

    fun movieDetailsViewModel(): MovieDetailsViewModel

    fun movieDetailsProcessor() : MovieDetailsProcessor

}