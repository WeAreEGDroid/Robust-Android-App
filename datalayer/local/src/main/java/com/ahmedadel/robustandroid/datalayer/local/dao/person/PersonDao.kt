package com.ahmedadel.robustandroid.datalayer.local.dao.person

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedadel.robustandroid.models.local.person.PersonLocal
import io.reactivex.Single

/**
 * Created at Tito on 3/15/19
 *
 * Dao for Person Local.
 */

@Dao
interface PersonDao {

    @get:Query("SELECT * FROM person")
    val getPersons: Single<List<PersonLocal>>

    @Query("SELECT * FROM person WHERE id = :personId")
    fun getPerson(personId: Int?): Single<PersonLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: PersonLocal)

    @Query("DELETE FROM person")
    fun deleteAll(): Int

}
