package com.ahmedadel.robustandroid.presentation.mvi.tv.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import com.ahmedadel.robustandroid.feature.tv.usecase.TVUseCase
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsProcessor
import com.ahmedadel.robustandroid.presentation.mvi.tv.mapper.TVMapper
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/20/19
 */

@Module
class TVDetailsModule {

    @Provides
    @PresentationScope
    fun providesTVMapper() = TVMapper()

    @Provides
    @PresentationScope
    fun providesTVDetailsProcessor(
        baseSchedulerProvider: BaseSchedulerProvider,
        tvUseCase: TVUseCase,
        mapper: TVMapper
    ): TVDetailsProcessor = TVDetailsProcessor(baseSchedulerProvider, tvUseCase, mapper)

}