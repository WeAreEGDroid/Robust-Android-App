package com.ahmedadel.robustandroid.presentation.mvp.movielist

import com.ahmedadel.robustandroid.feature.movie.usecase.MovieUseCase
import javax.inject.Inject

/**
 * Created at Tito on 3/17/19
 */

class MovieListModel
@Inject
constructor(private val movieUseCase: MovieUseCase) : MovieListContract.Model {

    override fun getMovies(pageNumber: Int) = movieUseCase.getMovies(pageNumber)

}