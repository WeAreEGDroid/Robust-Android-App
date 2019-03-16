package com.ahmedadel.robustandroid.core.di.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created at Tito on 3/16/19
 *
 * This class that will be used in the default behaviour of the app.
 */

class SchedulerProvider : BaseSchedulerProvider {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return Schedulers.computation()
    }
}