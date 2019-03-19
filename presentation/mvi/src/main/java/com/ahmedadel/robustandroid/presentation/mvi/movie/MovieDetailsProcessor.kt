package com.ahmedadel.robustandroid.presentation.mvi.movie

import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import com.ahmedadel.robustandroid.feature.movie.usecase.MovieUseCase
import com.ahmedadel.robustandroid.presentation.mvi.MviProcessor
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsResult.GetMovieDetailsResult
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsAction.GetMovieDetailsAction
import com.ahmedadel.robustandroid.presentation.mvi.movie.mapper.MovieMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created at Tito on 3/19/19
 */

class MovieDetailsProcessor
@Inject
constructor(
    baseSchedulerProvider: BaseSchedulerProvider,
    private val movieUseCase: MovieUseCase,
    private val mapper: MovieMapper
) : MviProcessor<MovieDetailsAction>(baseSchedulerProvider) {

    val getMovieDetailsProcessor: Observable<GetMovieDetailsResult> =
        actions.ofType<GetMovieDetailsAction>(GetMovieDetailsAction::class.java)
            .switchMap {
                movieUseCase.getMovie(it.movieId)
                    .map { movie ->
                        GetMovieDetailsResult.Success(mapper.mapToUiModel(movie))
                    }
                    .cast(GetMovieDetailsResult::class.java)
                    .onErrorReturn(GetMovieDetailsResult::Failure)
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .startWith(GetMovieDetailsResult.Loading)
                    .toObservable()
            }

}