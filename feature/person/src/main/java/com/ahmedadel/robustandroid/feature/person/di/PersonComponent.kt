package com.ahmedadel.robustandroid.feature.person.di

import com.ahmedadel.robustandroid.core.di.FeatureScope
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponent
import com.ahmedadel.robustandroid.feature.person.usecase.PersonUseCase
import dagger.Component

/**
 * Created at Tito on 3/15/19
 */

@FeatureScope
@Component(
    modules = [
        PersonModule::class
    ],
    dependencies = [
        DataSourceComponent::class
    ]
)
interface PersonComponent {

    fun getPersonUseCase(): PersonUseCase

}