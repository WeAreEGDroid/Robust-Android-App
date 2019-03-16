package com.ahmedadel.robustandroid.presentation.mvvm.home.di

import com.ahmedadel.robustandroid.core.di.FeatureScope
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponent
import com.ahmedadel.robustandroid.feature.movie.di.MovieModule
import com.ahmedadel.robustandroid.feature.movie.usecase.MovieUseCase
import com.ahmedadel.robustandroid.feature.person.di.PersonModule
import com.ahmedadel.robustandroid.feature.person.usecase.PersonUseCase
import com.ahmedadel.robustandroid.feature.tv.di.TVModule
import com.ahmedadel.robustandroid.feature.tv.usecase.TVUseCase
import dagger.Component

/**
 * Created at Tito on 3/16/19
 */

@FeatureScope
@Component(
    modules = [
        MovieModule::class,
        PersonModule::class,
        TVModule::class
    ],
    dependencies = [
        DataSourceComponent::class
    ]
)
interface HomeUseCasesComponent {

    fun movieUseCase(): MovieUseCase

    fun personUseCase(): PersonUseCase

    fun tvUseCase(): TVUseCase

}