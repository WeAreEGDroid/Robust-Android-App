package com.ahmedadel.robustandroid.tvlist.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.di.TVListComponent
import com.ahmedadel.robustandroid.tvlist.TVListActivity
import dagger.Component

/**
 * Created at Tito on 3/18/19
 */
@ActivityScope
@Component(
    modules = [
        TVListActivityModule::class
    ],
    dependencies = [
        TVListComponent::class
    ]
)
interface TVListActivityComponent {

    fun inject(tvListActivity: TVListActivity)

}