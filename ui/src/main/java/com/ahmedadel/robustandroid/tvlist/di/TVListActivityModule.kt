package com.ahmedadel.robustandroid.tvlist.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.tvlist.adapter.TVListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/18/19
 */

@Module
class TVListActivityModule {

    @Provides
    @ActivityScope
    fun provideTVListAdapter(): TVListAdapter = TVListAdapter()

}