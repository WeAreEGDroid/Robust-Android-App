package com.ahmedadel.robustandroid.feature.movie.usecase

import com.ahmedadel.robustandroid.feature.movie.repository.MovieRepository
import javax.inject.Inject

/**
 * Created at Tito on 3/15/19
 */

class MovieUseCase
@Inject
constructor(private val repository: MovieRepository) {

    fun getMovies(pageNumber: Int) = repository.getMovies(pageNumber)

    fun getMovie(movieId: Int) = repository.getMovie(movieId)

    fun getSimilarMovies(movieId: Int) = repository.getSimilarMovies(movieId)

    fun getRecommendationMovies(movieId: Int) = repository.getRecommendationMovies(movieId)

}