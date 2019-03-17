package com.ahmedadel.robustandroid.presentation.mvp.personlist.di

import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProviderModule
import com.ahmedadel.robustandroid.feature.person.di.PersonComponent
import com.ahmedadel.robustandroid.presentation.mvp.personlist.PersonListContract
import com.ahmedadel.robustandroid.presentation.mvp.personlist.PersonListPresenter
import dagger.Component

/**
 * Created at Tito on 3/17/19
 */

@PresentationScope
@Component(
    modules = [
        PersonListModule::class,
        BaseSchedulerProviderModule::class
    ],
    dependencies = [
        PersonComponent::class
    ]
)
interface PersonListComponent {

    fun personListPresenter(): PersonListPresenter

    fun personListModel(): PersonListContract.Model

}