package com.ahmedadel.robustandroid.presentation.mvi

import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.subjects.PublishSubject

/**
 * Created at Tito on 3/19/19
 */

abstract class MviProcessor<A : MviAction>
    (baseSchedulerProvider: BaseSchedulerProvider) {

    protected val subscribeOn: Scheduler = baseSchedulerProvider.io()
    protected val observeOn: Scheduler = baseSchedulerProvider.ui()

    val actions: PublishSubject<A> = PublishSubject.create<A>()

}