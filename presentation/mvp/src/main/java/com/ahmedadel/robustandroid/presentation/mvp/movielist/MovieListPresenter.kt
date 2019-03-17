package com.ahmedadel.robustandroid.presentation.mvp.movielist

import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import com.ahmedadel.robustandroid.presentation.mvp.BasePresenter
import com.ahmedadel.robustandroid.presentation.mvp.movielist.mapper.MovieMapper
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created at Tito on 3/17/19
 */

class MovieListPresenter
@Inject
constructor(
    baseSchedulerProvider: BaseSchedulerProvider,
    private val model: MovieListContract.Model,
    private val mapper: MovieMapper
) : BasePresenter<MovieListContract.View>(baseSchedulerProvider),
    MovieListContract.Presenter {

    override fun callMovies(pageNumber: Int) {

        execute(
            loadingConsumer = Consumer {
                getView()?.showLoading(true,pageNumber == 1)
            },
            successConsumer = Consumer { movieItemList ->
                getView()?.showLoading(false,pageNumber == 1)
                movieItemList?.let {
                    getView()?.showMovies(mapper.mapToUiModelList(it))
                }
            },
            throwableConsumer = Consumer { throwable ->
                getView()?.showErrorMessage(throwable.message)
            },
            useCase = model.getMovies(pageNumber)
        )

    }

}