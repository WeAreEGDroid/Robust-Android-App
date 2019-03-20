package com.ahmedadel.robustandroid.presentation.mvi

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created at Tito on 3/19/19
 *
 * Process intents coming from the view and provide a stream of states for the view to observe.
 * It is typed by an [MviIntent] and [MviViewState]
 */

abstract class MviViewModel<I : MviIntent, A : MviAction, S : MviViewState> :
    ViewModel() {

    val disposables: CompositeDisposable = CompositeDisposable()

    private val tag by lazy {
        javaClass.simpleName
    }

    abstract fun processIntents(intents: Observable<I>)

    abstract fun states(): Observable<S>

    protected abstract fun actionFromIntent(intent: I): A

    override fun onCleared() {
        disposables.clear()
    }
}