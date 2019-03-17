package com.ahmedadel.robustandroid.personlist.di

import android.app.Application
import com.ahmedadel.robustandroid.personlist.PersonListActivity
import com.ahmedadel.robustandroid.presentation.mvp.personlist.di.PersonListComponent
import com.ahmedadel.robustandroid.presentation.mvp.personlist.di.PersonListComponentWrapper

/**
 * Created at Tito on 3/18/19
 */

class PersonListActivityComponentWrapper {

    private lateinit var component: PersonListActivityComponent

    private fun initializeComponent(personListComponent: PersonListComponent) {
        component = DaggerPersonListActivityComponent.builder()
            .personListComponent(personListComponent)
            .build()
    }

    companion object {

        private var wrapper: PersonListActivityComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): PersonListActivityComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = PersonListActivityComponentWrapper()
                    wrapper!!.initializeComponent(PersonListComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun buildComponent(personListActivity: PersonListActivity) {
            getInstance(personListActivity.application).component.inject(personListActivity)
        }

    }

}