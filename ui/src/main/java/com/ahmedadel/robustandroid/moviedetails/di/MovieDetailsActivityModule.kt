package com.ahmedadel.robustandroid.moviedetails.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.moviedetails.adapter.RecommendationMoviesAdapter
import com.ahmedadel.robustandroid.moviedetails.adapter.SimilarMoviesAdapter
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/21/19
 */

@Module
class MovieDetailsActivityModule {

    @Provides
    @ActivityScope
    fun provideSimilarMoviesAdapter(): SimilarMoviesAdapter = SimilarMoviesAdapter()

    @Provides
    @ActivityScope
    fun provideRecommendationMoviesAdapter(): RecommendationMoviesAdapter = RecommendationMoviesAdapter()

}