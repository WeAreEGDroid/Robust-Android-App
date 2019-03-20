package com.ahmedadel.robustandroid.tvdetails.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.presentation.mvi.tv.di.TVDetailsComponent
import com.ahmedadel.robustandroid.tvdetails.TVDetailsActivity
import dagger.Component

/**
 * Created at Tito on 3/20/19
 */

@ActivityScope
@Component(
    dependencies = [
        TVDetailsComponent::class
    ]
)
interface TVDetailsActivityComponent {

    fun inject(tvDetailsActivity: TVDetailsActivity)

}