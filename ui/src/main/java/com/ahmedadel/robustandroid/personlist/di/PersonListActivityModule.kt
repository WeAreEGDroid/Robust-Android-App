package com.ahmedadel.robustandroid.personlist.di

import com.ahmedadel.robustandroid.di.ActivityScope
import com.ahmedadel.robustandroid.personlist.adapter.PersonListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/18/19
 */

@Module
class PersonListActivityModule {

    @Provides
    @ActivityScope
    fun providePersonListAdapter(): PersonListAdapter = PersonListAdapter()

}