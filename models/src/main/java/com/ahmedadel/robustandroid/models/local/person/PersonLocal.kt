package com.ahmedadel.robustandroid.models.local.person

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created at Tito on 3/15/19
 *
 * Person local model that will be used as a table in Room database.
 */

@Entity(tableName = "person")
data class PersonLocal(

    @PrimaryKey
    val id: Int = 0,

    @ColumnInfo(name = "popularity")
    val popularity: Double = 0.0,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "profile_path")
    val profilePath: String? = null,

    @ColumnInfo(name = "adult")
    val adult: Boolean = false

)
