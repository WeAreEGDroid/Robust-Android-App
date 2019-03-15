package com.ahmedadel.robustandroid.feature.tv.di

import com.ahmedadel.robustandroid.core.di.FeatureScope
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponent
import com.ahmedadel.robustandroid.feature.tv.usecase.TVUseCase
import dagger.Component

/**
 * Created at Tito on 3/15/19
 */

@FeatureScope
@Component(
    modules = [
        TVModule::class
    ],
    dependencies = [
        DataSourceComponent::class
    ]
)
interface TVComponent {

    fun getTVUseCase(): TVUseCase

}