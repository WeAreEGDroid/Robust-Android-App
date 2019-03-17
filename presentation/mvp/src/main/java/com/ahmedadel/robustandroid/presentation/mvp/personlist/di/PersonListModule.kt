package com.ahmedadel.robustandroid.presentation.mvp.personlist.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.feature.person.usecase.PersonUseCase
import com.ahmedadel.robustandroid.presentation.mvp.personlist.PersonListContract
import com.ahmedadel.robustandroid.presentation.mvp.personlist.PersonListModel
import com.ahmedadel.robustandroid.presentation.mvp.personlist.mapper.PersonMapper
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/17/19
 */

@Module
class PersonListModule {

    @Provides
    @PresentationScope
    fun providesPersonMapper() = PersonMapper()

    @Provides
    @PresentationScope
    fun providesPersonListModel(personUseCase: PersonUseCase): PersonListContract.Model = PersonListModel(personUseCase)

}