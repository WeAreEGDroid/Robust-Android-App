package com.ahmedadel.robustandroid.presentation.mvp.tvlist.uimodel

import com.ahmedadel.robustandroid.models.UiModel

/**
 * Created at Tito on 3/16/19
 */

data class TVUiModel (

    val id: Int = 0,

    val name: String? = null,

    val overview: String? = null,

    val originalName: String? = null,

    val originalLanguage: String? = null,

    val posterPath: String? = null,

    val voteCount: Int = 0

) : UiModel