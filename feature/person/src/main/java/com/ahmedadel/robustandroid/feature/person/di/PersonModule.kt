package com.ahmedadel.robustandroid.feature.person.di

import com.ahmedadel.robustandroid.core.di.FeatureScope
import com.ahmedadel.robustandroid.datalayer.local.dao.person.PersonDao
import com.ahmedadel.robustandroid.datalayer.remote.ApiService
import com.ahmedadel.robustandroid.feature.person.mapper.PersonMapper
import com.ahmedadel.robustandroid.feature.person.repository.PersonRepository
import com.ahmedadel.robustandroid.feature.person.usecase.PersonUseCase
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/15/19
 */

@Module
class PersonModule(
    private val local: PersonDao,
    private val remote: ApiService
) {

    @Provides
    @FeatureScope
    fun providesPersonMapper() = PersonMapper()

    @Provides
    @FeatureScope
    fun providePersonRepository(mapper: PersonMapper) = PersonRepository(local, remote, mapper)

    @Provides
    @FeatureScope
    fun providePersonUseCase(repository: PersonRepository) = PersonUseCase(repository)

}