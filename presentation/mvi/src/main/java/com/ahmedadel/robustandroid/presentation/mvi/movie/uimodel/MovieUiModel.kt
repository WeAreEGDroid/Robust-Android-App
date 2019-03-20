package com.ahmedadel.robustandroid.presentation.mvi.movie.uimodel

import com.ahmedadel.robustandroid.models.UiModel

/**
 * Created at Tito on 3/19/19
 */

data class MovieUiModel(

    val id: Int = 0,

    val title: String? = null,

    val overview: String? = null,

    val originalLanguage: String? = null,

    val originalTitle: String? = null,

    val posterPath: String? = null,

    val releaseDate: String? = null,

    val voteAverage: Double = 0.0,

    val isAdult: Boolean = false

) : UiModel