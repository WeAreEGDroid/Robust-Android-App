package com.ahmedadel.robustandroid.models.remote.tv

import com.squareup.moshi.Json

data class TVRemote(

    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "first_air_date")
    val firstAirDate: String? = null,

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "popularity")
    val popularity: Double = 0.0,

    @Json(name = "vote_average")
    val voteAverage: Double = 0.0,

    @Json(name = "original_name")
    val originalName: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "vote_count")
    val voteCount: Int = 0

)