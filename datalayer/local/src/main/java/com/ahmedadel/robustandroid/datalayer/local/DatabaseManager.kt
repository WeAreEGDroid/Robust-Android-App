package com.ahmedadel.robustandroid.datalayer.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmedadel.robustandroid.datalayer.local.dao.movie.MovieDao
import com.ahmedadel.robustandroid.datalayer.local.dao.person.PersonDao
import com.ahmedadel.robustandroid.datalayer.local.dao.tv.TVDao
import com.ahmedadel.robustandroid.models.local.movie.MovieLocal
import com.ahmedadel.robustandroid.models.local.person.PersonLocal
import com.ahmedadel.robustandroid.models.local.tv.TVLocal

/**
 * Created at Tito on 3/15/19
 *
 * Room Database Manager for the whole project with move-app name.
 */

@Database(entities = [MovieLocal::class, PersonLocal::class, TVLocal::class], version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun personDao(): PersonDao

    abstract fun tvDao(): TVDao

    companion object {

        @Synchronized
        fun getInstance(context: Context): DatabaseManager {
            return Room.databaseBuilder(
                context.applicationContext,
                DatabaseManager::class.java, "movie-app.db"
            ).build()
        }

    }

}
