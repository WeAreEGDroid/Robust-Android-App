package com.ahmedadel.robustandroid.presentation.mvp.personlist.uimodel

import com.ahmedadel.robustandroid.models.UiModel

/**
 * Created at Tito on 3/17/19
 */

data class PersonUiModel(

    val id: Int = 0,

    val popularity: Double = 0.0,

    val name: String? = null,

    val profilePath: String? = null,

    val isAdult: Boolean = false

) : UiModel