package com.ahmedadel.robustandroid.models.remote.person

import com.squareup.moshi.Json

data class PersonListRemote(

    @Json(name = "page")
    val page: Int = 0,

    @Json(name = "total_pages")
    val totalPages: Int = 0,

    @Json(name = "results")
    val persons: List<PersonRemote>? = null,

    @Json(name = "total_results")
    val totalResults: Int = 0

)