package com.ahmedadel.robustandroid.feature.tv.repository

import com.ahmedadel.robustandroid.datalayer.local.dao.tv.TVDao
import com.ahmedadel.robustandroid.datalayer.remote.ApiService
import com.ahmedadel.robustandroid.feature.tv.entity.TVEntity
import com.ahmedadel.robustandroid.feature.tv.mapper.TVMapper
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created at Tito on 3/15/19
 */

class TVRepository
@Inject
constructor(
    private val local: TVDao,
    private val remote: ApiService,
    private val mapper: TVMapper
) {

    fun getTVs(pageNumber: Int): Flowable<List<TVEntity>?> {

        val localTV =
            local.getTVs.map { tvLocalList ->
                tvLocalList.map { tvLocal ->
                    mapper.mapFromLocalToEntity(tvLocal)
                }
            }

        val remoteTV = if (pageNumber == 1) {
            remote.getTVs(pageNumber).map { getTVsResponse ->
                getTVsResponse.tVs?.map { tvRemote ->
                    local.insertTV(mapper.mapFromRemoteToLocal(tvRemote))
                    mapper.mapFromRemoteToEntity(tvRemote)
                }
            }
        } else {
            remote.getTVs(pageNumber).map { getTVsResponse ->
                getTVsResponse.tVs?.map { tvRemote ->
                    mapper.mapFromRemoteToEntity(tvRemote)
                }
            }
        }

        if (pageNumber == 1)
            return Single.concat<List<TVEntity>>(localTV, remoteTV)

        return remoteTV.toFlowable()
    }

    fun getTV(tvId: Int): Flowable<TVEntity> {

        val localTV =
            local.getTV(tvId).map { tvLocal ->
                mapper.mapFromLocalToEntity(tvLocal)
            }.onErrorReturn {
                TVEntity()
            }

        val remoteTV =
            remote.getTV(tvId).map { tvRemote ->
                local.insertTV(mapper.mapFromRemoteToLocal(tvRemote))
                mapper.mapFromRemoteToEntity(tvRemote)
            }

        return Single.concat<TVEntity>(localTV, remoteTV)
    }

    @Suppress("unused")
    fun clearDatabase(): Single<Int> {
        return Observable.fromCallable { local.deleteAll() }.firstOrError()
    }

}