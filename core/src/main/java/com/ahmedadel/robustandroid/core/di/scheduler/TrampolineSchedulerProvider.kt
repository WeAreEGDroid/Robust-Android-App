package com.ahmedadel.robustandroid.core.di.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created at Tito on 3/16/19
 *
 * This scheduler will be used in unit testing phase.
 */

class TrampolineSchedulerProvider : BaseSchedulerProvider {

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}