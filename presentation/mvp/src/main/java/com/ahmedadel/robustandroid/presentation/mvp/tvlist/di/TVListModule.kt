package com.ahmedadel.robustandroid.presentation.mvp.tvlist.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.feature.tv.usecase.TVUseCase
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.TVListContract
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.TVListModel
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.mapper.TVMapper
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/17/19
 */

@Module
class TVListModule {

    @Provides
    @PresentationScope
    fun providesTVMapper() = TVMapper()

    @Provides
    @PresentationScope
    fun providesTVListModel(tvUseCase: TVUseCase): TVListContract.Model = TVListModel(tvUseCase)

}