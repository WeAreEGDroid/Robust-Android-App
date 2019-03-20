package com.ahmedadel.robustandroid.presentation.mvi.tv.di

import androidx.lifecycle.ViewModelProvider
import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.core.di.ViewModelFactoryModule
import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProviderModule
import com.ahmedadel.robustandroid.feature.tv.di.TVComponent
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsViewModel
import dagger.Component

/**
 * Created at Tito on 3/20/19
 */

@PresentationScope
@Component(
    modules = [
        ViewModelFactoryModule::class,
        TVDetailsViewModelModule::class,
        TVDetailsModule::class,
        BaseSchedulerProviderModule::class
    ],
    dependencies = [
        TVComponent::class
    ]
)
interface TVDetailsComponent {

    fun tvDetailsViewModel(): TVDetailsViewModel

    fun viewModelFactory(): ViewModelProvider.Factory

}