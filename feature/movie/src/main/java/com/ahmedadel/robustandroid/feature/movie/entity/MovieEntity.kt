package com.ahmedadel.robustandroid.feature.movie.entity

import com.ahmedadel.robustandroid.models.EntityModel

/**
 * Created at Tito on 3/15/19
 */

data class MovieEntity(

    val id: Int = 0,

    val overview: String? = null,

    val originalLanguage: String? = null,

    val originalTitle: String? = null,

    val title: String? = null,

    val posterPath: String? = null,

    val releaseDate: String? = null,

    val popularity: Double = 0.0,

    val voteAverage: Double = 0.0,

    val isAdult: Boolean = false,

    val voteCount: Int = 0

) : EntityModel