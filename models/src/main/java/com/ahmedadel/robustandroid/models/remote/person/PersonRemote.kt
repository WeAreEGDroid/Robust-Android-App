package com.ahmedadel.robustandroid.models.remote.person

import com.squareup.moshi.Json

data class PersonRemote(

    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "popularity")
    val popularity: Double = 0.0,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "profile_path")
    val profilePath: String? = null,

    @Json(name = "adult")
    val isAdult: Boolean = false

)