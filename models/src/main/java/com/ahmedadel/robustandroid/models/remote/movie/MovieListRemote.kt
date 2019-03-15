package com.ahmedadel.robustandroid.models.remote.movie

import com.squareup.moshi.Json

data class MovieListRemote(

    @Json(name = "page")
    val page: Int = 0,

    @Json(name = "total_pages")
    val totalPages: Int = 0,

    @Json(name = "results")
    val movies: List<MovieRemote>? = null,

    @Json(name = "total_results")
    val totalResults: Int = 0

)