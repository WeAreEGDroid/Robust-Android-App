package com.ahmedadel.robustandroid.datalayer.datasource.remote.di

/**
 * Created at Tito on 3/15/19
 *
 * Wrapper class with singleton behaviour to initialize Dagger Remote Component.
 */

open class RemoteComponentWrapper {

    private lateinit var component: com.ahmedadel.robustandroid.datalayer.datasource.remote.di.RemoteComponent

    private fun initializeComponent() {
        component = DaggerRemoteComponent.builder()
            .remoteModule(RemoteModule())
            .build()
    }

    companion object {

        private var wrapper: RemoteComponentWrapper? = null

        @Synchronized
        private fun getInstance(): RemoteComponentWrapper {
            if (wrapper == null) {
                if (wrapper == null) {
                    wrapper =
                        RemoteComponentWrapper()
                    wrapper!!.initializeComponent()
                }
            }
            return wrapper!!
        }

        fun getComponent() = getInstance().component
    }

}