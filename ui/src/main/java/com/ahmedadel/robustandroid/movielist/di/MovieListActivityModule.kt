package com.ahmedadel.robustandroid.movielist.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.movielist.adapter.MovieListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/17/19
 */

@Module
class MovieListActivityModule {

    @Provides
    @ActivityScope
    fun provideMovieListAdapter(): MovieListAdapter = MovieListAdapter()

}