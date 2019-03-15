package com.ahmedadel.robustandroid.datalayer.local.dao.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedadel.robustandroid.models.local.tv.TVLocal
import io.reactivex.Single

/**
 * Created at Tito on 3/15/19
 *
 * Dao for TV Local.
 */

@Dao
interface TVDao {

    @get:Query("SELECT * FROM tv ORDER BY name ASC")
    val getTVs: Single<List<TVLocal>>

    @Query("SELECT * FROM tv WHERE id = :tvId")
    fun getTV(tvId: Int?): Single<TVLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTV(tv: TVLocal)

    @Query("DELETE FROM tv")
    fun deleteAll(): Int

}
