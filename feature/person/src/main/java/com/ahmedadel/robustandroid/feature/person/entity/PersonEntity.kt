package com.ahmedadel.robustandroid.feature.person.entity

import com.ahmedadel.robustandroid.models.EntityModel

/**
 * Created at Tito on 3/15/19
 */

data class PersonEntity(

    val id: Int = 0,

    val popularity: Double = 0.0,

    val name: String? = null,

    val profilePath: String? = null,

    val isAdult: Boolean = false

) : EntityModel