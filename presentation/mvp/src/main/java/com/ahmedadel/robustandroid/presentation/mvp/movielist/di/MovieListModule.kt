package com.ahmedadel.robustandroid.presentation.mvp.movielist.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.feature.movie.usecase.MovieUseCase
import com.ahmedadel.robustandroid.presentation.mvp.movielist.MovieListContract
import com.ahmedadel.robustandroid.presentation.mvp.movielist.MovieListModel
import com.ahmedadel.robustandroid.presentation.mvp.movielist.mapper.MovieMapper
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/17/19
 */

@Module
class MovieListModule {

    @Provides
    @PresentationScope
    fun providesMovieMapper() = MovieMapper()

    @Provides
    @PresentationScope
    fun providesMovieListModel(movieUseCase: MovieUseCase): MovieListContract.Model = MovieListModel(movieUseCase)

}