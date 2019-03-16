package com.ahmedadel.robustandroid.home.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.home.HomeActivity
import com.ahmedadel.robustandroid.presentation.mvvm.home.di.HomeViewModelComponent
import com.ahmedadel.robustandroid.presentation.mvvm.home.di.HomeViewModelModule
import dagger.Component

/**
 * Created at Tito on 3/16/19
 */

@ActivityScope
@Component(
    modules = [
        HomeActivityModule::class
    ],
    dependencies = [
        HomeViewModelComponent::class
    ]
)
interface HomeActivityComponent {

    fun inject(homeActivity: HomeActivity)

}