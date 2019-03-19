package com.ahmedadel.robustandroid.presentation.mvi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created at Tito on 3/19/19
 *
 * Process intents coming from the view and provide a stream of states for the view to observe.
 * It is typed by an [MviIntent] and [MviViewState]
 */

abstract class MviViewModel<I : MviIntent, A : MviAction, S : MviViewState> :
    ViewModel() {

    protected val disposables: CompositeDisposable = CompositeDisposable()

    protected val state = MutableLiveData<S>()

    private val tag by lazy {
        javaClass.simpleName
    }

    fun states(): LiveData<S> =
        MediatorLiveData<S>().apply {
            addSource(state) { data ->
                Log.d("MviViewModel", "$tag: Received state: $data")
                setValue(data)
            }
        }

    fun processIntent(intent: I) {
        Log.d("MviViewModel", "$tag: Received intent: $intent")
        getProcessor().actions.onNext(actionFromIntent(intent))
    }

    protected abstract fun actionFromIntent(intent: I): A

    protected abstract fun getProcessor(): MviProcessor<A>

    override fun onCleared() {
        disposables.clear()
    }
}