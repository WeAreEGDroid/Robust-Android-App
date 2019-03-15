package com.ahmedadel.robustandroid.feature.tv.di

import com.ahmedadel.robustandroid.core.di.FeatureScope
import com.ahmedadel.robustandroid.datalayer.local.dao.tv.TVDao
import com.ahmedadel.robustandroid.datalayer.remote.ApiService
import com.ahmedadel.robustandroid.feature.tv.mapper.TVMapper
import com.ahmedadel.robustandroid.feature.tv.repository.TVRepository
import com.ahmedadel.robustandroid.feature.tv.usecase.TVUseCase
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/15/19
 */

@Module
class TVModule(
    private val local: TVDao,
    private val remote: ApiService
) {

    @Provides
    @FeatureScope
    fun providesTVMapper() = TVMapper()

    @Provides
    @FeatureScope
    fun provideTVRepository(mapper: TVMapper) = TVRepository(local, remote, mapper)

    @Provides
    @FeatureScope
    fun provideTVUseCase(repository: TVRepository) = TVUseCase(repository)

}