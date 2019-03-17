package com.ahmedadel.robustandroid.presentation.mvp.personlist.di

import android.app.Application
import com.ahmedadel.robustandroid.feature.person.di.PersonComponent
import com.ahmedadel.robustandroid.feature.person.di.PersonComponentWrapper

/**
 * Created at Tito on 3/17/19
 */

open class PersonListComponentWrapper {

    private lateinit var component: PersonListComponent

    private fun initializeComponent(personComponent: PersonComponent) {
        component = DaggerPersonListComponent.builder()
            .personComponent(personComponent)
            .build()
    }

    companion object {

        private var wrapper: PersonListComponentWrapper? = null

        @Synchronized
        private fun getInstance(application: Application): PersonListComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper = PersonListComponentWrapper()
                    wrapper!!.initializeComponent(PersonComponentWrapper.getComponent(application))
                }
            }
            return wrapper!!
        }

        fun getComponent(application: Application) = getInstance(application).component

    }

}