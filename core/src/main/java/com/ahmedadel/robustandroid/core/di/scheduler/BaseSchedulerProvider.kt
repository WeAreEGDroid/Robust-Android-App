package com.ahmedadel.robustandroid.core.di.scheduler

import io.reactivex.Scheduler

/**
 * Created at Tito on 3/16/19
 *
 * Base class for scheduler provider.
 */

interface BaseSchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

}