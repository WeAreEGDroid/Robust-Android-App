package com.ahmedadel.robustandroid.home.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.home.adapter.HomeMovieAdapter
import com.ahmedadel.robustandroid.home.adapter.HomePersonAdapter
import com.ahmedadel.robustandroid.home.adapter.HomeTVAdapter
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/16/19
 */

@Module
class HomeActivityModule {

    @Provides
    @ActivityScope
    fun provideMovieAdapter(): HomeMovieAdapter = HomeMovieAdapter()

    @Provides
    @ActivityScope
    fun providePersonAdapter(): HomePersonAdapter = HomePersonAdapter()

    @Provides
    @ActivityScope
    fun provideTVAdapter(): HomeTVAdapter = HomeTVAdapter()

}