package com.ahmedadel.robustandroid.models.remote.movie

import com.squareup.moshi.Json

data class MovieRemote(

    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    @Json(name = "video")
    val isVideo: Boolean = false,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    @Json(name = "popularity")
    val popularity: Double = 0.0,

    @Json(name = "vote_average")
    val voteAverage: Double = 0.0,

    @Json(name = "adult")
    val isAdult: Boolean = false,

    @Json(name = "vote_count")
    val voteCount: Int = 0

)