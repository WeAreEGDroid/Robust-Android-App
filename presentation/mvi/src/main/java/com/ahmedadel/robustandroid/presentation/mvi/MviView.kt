package com.ahmedadel.robustandroid.presentation.mvi

import io.reactivex.Observable

/**
 * Created at Tito on 3/19/19
 *
 * The view must provide intents for the [MviViewModel] and also be able to render new state coming from
 * [MviViewModel].
 * It is typed by an [MviIntent] and [MviViewState]
 */

interface MviView<I : MviIntent, in S : MviViewState> {
    fun intents(): Observable<I>
    fun render(state: S)
}