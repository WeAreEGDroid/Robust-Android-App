package com.ahmedadel.robustandroid.presentation.mvi

/**
 * Created at Tito on 3/19/19
 */

typealias Reducer<S, R> = (previousState: S, result: R) -> S