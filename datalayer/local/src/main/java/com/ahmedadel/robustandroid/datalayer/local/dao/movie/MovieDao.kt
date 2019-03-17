package com.ahmedadel.robustandroid.datalayer.local.dao.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedadel.robustandroid.models.local.movie.MovieLocal
import io.reactivex.Single

/**
 * Created at Tito on 3/15/19
 *
 * Dao for Movie Local.
 */

@Dao
interface MovieDao {

    @get:Query("SELECT * FROM movie")
    val getMovies: Single<List<MovieLocal>>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovie(movieId: Int?): Single<MovieLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieLocal)

    @Query("DELETE FROM movie")
    fun deleteAll(): Int

}
