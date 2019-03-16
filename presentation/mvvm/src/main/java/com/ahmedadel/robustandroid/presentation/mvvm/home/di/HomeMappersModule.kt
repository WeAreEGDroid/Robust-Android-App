package com.ahmedadel.robustandroid.presentation.mvvm.home.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.presentation.mvvm.home.mapper.MovieMapper
import com.ahmedadel.robustandroid.presentation.mvvm.home.mapper.PersonMapper
import com.ahmedadel.robustandroid.presentation.mvvm.home.mapper.TVMapper
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/16/19
 */

@Module
class HomeMappersModule {

    @Provides
    @PresentationScope
    fun providesMovieMapper() = MovieMapper()

    @Provides
    @PresentationScope
    fun providesPersonMapper() = PersonMapper()

    @Provides
    @PresentationScope
    fun providesTVMapper() = TVMapper()

}