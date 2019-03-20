package com.ahmedadel.robustandroid.presentation.mvi.movie.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import com.ahmedadel.robustandroid.feature.movie.usecase.MovieUseCase
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsProcessor
import com.ahmedadel.robustandroid.presentation.mvi.movie.mapper.MovieMapper
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/19/19
 */

@Module
class MovieDetailsModule {

    @Provides
    @PresentationScope
    fun providesMovieMapper() = MovieMapper()

    @Provides
    @PresentationScope
    fun providesMovieDetailsProcessor(
        baseSchedulerProvider: BaseSchedulerProvider,
        movieUseCase: MovieUseCase,
        mapper: MovieMapper
    ): MovieDetailsProcessor = MovieDetailsProcessor(baseSchedulerProvider, movieUseCase, mapper)

}