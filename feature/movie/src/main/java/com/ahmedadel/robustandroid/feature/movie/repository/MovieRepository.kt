package com.ahmedadel.robustandroid.feature.movie.repository

import com.ahmedadel.robustandroid.datalayer.local.dao.movie.MovieDao
import com.ahmedadel.robustandroid.datalayer.remote.ApiService
import com.ahmedadel.robustandroid.feature.movie.entity.MovieEntity
import com.ahmedadel.robustandroid.feature.movie.mapper.MovieMapper
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created at Tito on 3/15/19
 */

class MovieRepository
@Inject
constructor(
    private val local: MovieDao,
    private val remote: ApiService,
    private val mapper: MovieMapper
) {

    fun getMovies(pageNumber: Int): Flowable<List<MovieEntity>?> {

        val localMovie =
            local.getMovies.map { movieLocalList ->
                movieLocalList.map { movieLocal ->
                    mapper.mapFromLocalToItem(movieLocal)
                }
            }

        val remoteMovie =
            remote.getMovies(pageNumber).map { getMovieResponse ->
                getMovieResponse.movies?.map { movieRemote ->
                    if (pageNumber == 1)
                        local.insertMovie(mapper.mapFromRemoteToLocal(movieRemote))
                    mapper.mapFromRemoteToItem(movieRemote)
                }
            }

        if (pageNumber == 1)
            return Single.concat<List<MovieEntity>>(localMovie, remoteMovie)

        return remoteMovie.toFlowable()
    }

    fun getMovie(movieId: Int): Flowable<MovieEntity> {

        val localMovie =
            local.getMovie(movieId).map { movieLocal ->
                mapper.mapFromLocalToItem(movieLocal)
            }.onErrorReturn {
                MovieEntity()
            }

        val remoteMovie =
            remote.getMovie(movieId).map { movieRemote ->
                local.insertMovie(mapper.mapFromRemoteToLocal(movieRemote))
                mapper.mapFromRemoteToItem(movieRemote)
            }

        return Single.concat<MovieEntity>(localMovie, remoteMovie)
    }

    @Suppress("unused")
    fun clearDatabase(): Single<Int> {
        return Observable.fromCallable { local.deleteAll() }.firstOrError()
    }
}