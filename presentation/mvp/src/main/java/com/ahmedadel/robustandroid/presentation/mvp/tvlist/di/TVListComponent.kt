package com.ahmedadel.robustandroid.presentation.mvp.tvlist.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProviderModule
import com.ahmedadel.robustandroid.feature.tv.di.TVComponent
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.TVListContract
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.TVListPresenter
import dagger.Component

/**
 * Created at Tito on 3/17/19
 */

@PresentationScope
@Component(
    modules = [
        TVListModule::class,
        BaseSchedulerProviderModule::class
    ],
    dependencies = [
        TVComponent::class
    ]
)
interface TVListComponent {

    fun tvListPresenter(): TVListPresenter

    fun personListModel(): TVListContract.Model

}