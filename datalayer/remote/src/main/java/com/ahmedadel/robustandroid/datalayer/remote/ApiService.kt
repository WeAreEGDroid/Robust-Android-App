package com.ahmedadel.robustandroid.datalayer.remote

import com.ahmedadel.robustandroid.models.remote.movie.MovieListRemote
import com.ahmedadel.robustandroid.models.remote.movie.MovieRemote

import com.ahmedadel.robustandroid.models.remote.person.PersonListRemote
import com.ahmedadel.robustandroid.models.remote.person.PersonRemote

import com.ahmedadel.robustandroid.models.remote.tv.TVListRemote
import com.ahmedadel.robustandroid.models.remote.tv.TVRemote

import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created at Tito on 3/15/19
 *
 * API Endpoints for Movie, TV and Person APIs
 */

interface ApiService {

    // Movie APIs
    @GET("/3/movie/popular")
    fun getMovies(@Query("page") pageNumber: Int?): Single<MovieListRemote>

    @GET("/3/movie/{id}")
    fun getMovie(@Path("id") movieId: Int?): Single<MovieRemote>
    // ---------------------------------------------------------------------------------------- //

    // TV APIs
    @GET("/3/tv/popular")
    fun getTVs(@Query("page") pageNumber: Int?): Single<TVListRemote>

    @GET("/3/tv/{id}")
    fun getTV(@Path("id") tvId: Int?): Single<TVRemote>
    // ---------------------------------------------------------------------------------------- //

    // Person APIs
    @GET("/3/person/popular")
    fun getPersons(@Query("page") pageNumber: Int?): Single<PersonListRemote>

    @GET("/3/person/{id}")
    fun getPerson(@Path("id") personId: Int?): Single<PersonRemote>
    // ---------------------------------------------------------------------------------------- //

}