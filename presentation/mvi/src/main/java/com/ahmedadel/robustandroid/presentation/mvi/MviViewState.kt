package com.ahmedadel.robustandroid.presentation.mvi

/**
 * Created at Tito on 3/19/19
 *
 * This interface just creates a type that we will use to tag state classes with and does not need any functions
 * till now.
 */

interface MviViewState

abstract class CompoundViewState<
        V1 : MviViewState,
        V2 : MviViewState,
        V3 : MviViewState>(
    open val first: V1,
    open val second: V2,
    open val third: V3
) : MviViewState