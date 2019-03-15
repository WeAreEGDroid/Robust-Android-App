package com.ahmedadel.robustandroid.feature.movie.di

import com.ahmedadel.robustandroid.core.di.FeatureScope
import com.ahmedadel.robustandroid.datalayer.local.dao.movie.MovieDao
import com.ahmedadel.robustandroid.datalayer.remote.ApiService
import com.ahmedadel.robustandroid.feature.movie.mapper.MovieMapper
import com.ahmedadel.robustandroid.feature.movie.repository.MovieRepository
import com.ahmedadel.robustandroid.feature.movie.usecase.MovieUseCase
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/15/19
 */

@Module
class MovieModule(
    private val local: MovieDao,
    private val remote: ApiService
) {

    @Provides
    @FeatureScope
    fun providesMovieMapper() = MovieMapper()

    @Provides
    @FeatureScope
    fun provideMovieRepository(mapper: MovieMapper) = MovieRepository(local, remote, mapper)

    @Provides
    @FeatureScope
    fun provideMovieUseCase(repository: MovieRepository) = MovieUseCase(repository)

}