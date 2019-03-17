package com.ahmedadel.robustandroid.presentation.mvp

import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription
import java.lang.ref.WeakReference

/**
 * Created at Tito on 3/17/19
 */

abstract class BasePresenter<V>
constructor(
    baseSchedulerProvider: BaseSchedulerProvider
) {

    private var view: WeakReference<V>? = null

    private val subscribeOn: Scheduler = baseSchedulerProvider.io()
    private val observeOn: Scheduler = baseSchedulerProvider.ui()
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun setView(view: V) {
        this.view = WeakReference(view)
    }

    protected fun getView(): V? = view?.get()

    protected fun <T> execute(
        loadingConsumer: Consumer<Subscription>,
        successConsumer: Consumer<T>,
        throwableConsumer: Consumer<Throwable>,
        useCase: Flowable<T>
    ) {
        val observable = useCase
            .doOnSubscribe(loadingConsumer)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
        addDisposable(observable.subscribe(successConsumer, throwableConsumer))
    }

    private fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun clear() {
        dispose()
    }

}