package com.ahmedadel.robustandroid.feature.movie.di

import com.ahmedadel.robustandroid.core.di.FeatureScope
import com.ahmedadel.robustandroid.datalayer.datasource.both.di.DataSourceComponent
import com.ahmedadel.robustandroid.datalayer.datasource.local.di.LocalComponent
import com.ahmedadel.robustandroid.datalayer.datasource.remote.di.RemoteComponent
import com.ahmedadel.robustandroid.feature.movie.usecase.MovieUseCase
import dagger.Component

/**
 * Created at Tito on 3/15/19
 */

@FeatureScope
@Component(
    modules = [
        MovieModule::class
    ],
    dependencies = [
        DataSourceComponent::class
    ]
)
interface MovieComponent {

    fun getMovieUseCase(): MovieUseCase

}
