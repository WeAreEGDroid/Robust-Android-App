package com.ahmedadel.robustandroid.presentation.mvp.movielist

import com.ahmedadel.robustandroid.feature.movie.entity.MovieEntity
import com.ahmedadel.robustandroid.presentation.mvp.BaseView
import com.ahmedadel.robustandroid.presentation.mvp.movielist.uimodel.MovieUiModel
import io.reactivex.Flowable

/**
 * Created at Tito on 3/17/19
 */

interface MovieListContract {

    interface View : BaseView {

        fun showMovies(movies: List<MovieUiModel>)

    }

    interface Presenter {

        fun callMovies(pageNumber: Int)

    }

    interface Model {

        fun getMovies(pageNumber: Int): Flowable<List<MovieEntity>?>

    }

}