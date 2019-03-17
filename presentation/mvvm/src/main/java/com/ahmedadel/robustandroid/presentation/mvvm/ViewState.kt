package com.ahmedadel.robustandroid.presentation.mvvm

/**
 * Created at ahmedadel on 2/25/19
 *
 *
 * A generic class that contains data and status about loading this data.
 */

class ViewState<T> private constructor(
    val status: Status,
    val data: T?,
    val message: String?
) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(data: T): ViewState<T> {
            return ViewState(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String?): ViewState<T> {
            return ViewState(
                Status.ERROR,
                null,
                msg
            )
        }

        fun <T> loading(): ViewState<T> {
            return ViewState(
                Status.LOADING,
                null,
                null
            )
        }
    }

}
