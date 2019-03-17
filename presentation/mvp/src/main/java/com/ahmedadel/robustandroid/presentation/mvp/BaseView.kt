package com.ahmedadel.robustandroid.presentation.mvp

interface BaseView {

    fun showErrorMessage(message: String?)

    fun showLoading(isLoading: Boolean, isFirstPage: Boolean)

}