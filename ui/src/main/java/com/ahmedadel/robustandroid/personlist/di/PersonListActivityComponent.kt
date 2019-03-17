package com.ahmedadel.robustandroid.personlist.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.personlist.PersonListActivity
import com.ahmedadel.robustandroid.presentation.mvp.personlist.di.PersonListComponent
import dagger.Component

/**
 * Created at Tito on 3/18/19
 */

@ActivityScope
@Component(
    modules = [
        PersonListActivityModule::class
    ],
    dependencies = [
        PersonListComponent::class
    ]
)
interface PersonListActivityComponent {

    fun inject(personListActivity: PersonListActivity)

}