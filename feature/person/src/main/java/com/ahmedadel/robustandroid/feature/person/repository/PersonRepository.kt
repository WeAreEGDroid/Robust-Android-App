package com.ahmedadel.robustandroid.feature.person.repository

import com.ahmedadel.robustandroid.datalayer.local.dao.person.PersonDao
import com.ahmedadel.robustandroid.datalayer.remote.ApiService
import com.ahmedadel.robustandroid.feature.person.entity.PersonEntity
import com.ahmedadel.robustandroid.feature.person.mapper.PersonMapper
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created at Tito on 3/15/19
 */

class PersonRepository
@Inject
constructor(
    private val local: PersonDao,
    private val remote: ApiService,
    private val mapper: PersonMapper
) {

    fun getPersons(pageNumber: Int): Flowable<List<PersonEntity>?> {

        val localPerson =
            local.getPersons.map { personLocalList ->
                personLocalList.map { personLocal ->
                    mapper.mapFromLocalToItem(personLocal)
                }
            }

        val remotePerson =
            remote.getPersons(pageNumber).map { getPersonResponse ->
                getPersonResponse.persons?.map { personRemote ->
                    if (pageNumber == 1)
                        local.insertPerson(mapper.mapFromRemoteToLocal(personRemote))
                    mapper.mapFromRemoteToItem(personRemote)
                }
            }

        if (pageNumber == 1)
            return Single.concat<List<PersonEntity>>(localPerson, remotePerson)

        return remotePerson.toFlowable()
    }

    fun getPerson(personId: Int): Flowable<PersonEntity> {

        val localPerson =
            local.getPerson(personId).map { personLocal ->
                mapper.mapFromLocalToItem(personLocal)
            }.onErrorReturn {
                PersonEntity()
            }

        val remotePerson =
            remote.getPerson(personId).map { personRemote ->
                local.insertPerson(mapper.mapFromRemoteToLocal(personRemote))
                mapper.mapFromRemoteToItem(personRemote)
            }

        return Single.concat<PersonEntity>(localPerson, remotePerson)
    }

    @Suppress("unused")
    fun clearDatabase(): Single<Int> {
        return Observable.fromCallable { local.deleteAll() }.firstOrError()
    }

}