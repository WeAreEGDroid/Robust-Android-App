package com.ahmedadel.robustandroid.presentation.mvi.movie

import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import com.ahmedadel.robustandroid.feature.movie.usecase.MovieUseCase
import com.ahmedadel.robustandroid.presentation.mvi.MviProcessor
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsAction.*
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsResult.*
import com.ahmedadel.robustandroid.presentation.mvi.movie.mapper.MovieMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
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

    private val getMovieDetailsProcessor: ObservableTransformer<GetMovieDetailsAction, GetMovieDetailsResult> =
        ObservableTransformer { actions ->
            actions.flatMap {
                movieUseCase.getMovie(it.movieId)
                    .map { movie -> GetMovieDetailsResult.Success(mapper.mapToUiModel(movie)) }
                    .cast(GetMovieDetailsResult::class.java)
                    .onErrorReturn(GetMovieDetailsResult::Failure)
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .startWith(GetMovieDetailsResult.Loading)
                    .toObservable()
            }
        }

    private val getSimilarMoviesProcessor: ObservableTransformer<GetSimilarMoviesAction, GetSimilarMoviesResult> =
        ObservableTransformer { actions ->
            actions.flatMap {
                movieUseCase.getSimilarMovies(it.movieId)
                    .map { movies -> GetSimilarMoviesResult.Success(mapper.mapToUiModelList(movies)) }
                    .cast(GetSimilarMoviesResult::class.java)
                    .onErrorReturn(GetSimilarMoviesResult::Failure)
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .startWith(GetSimilarMoviesResult.Loading)
                    .toObservable()
            }
        }

    private val getRecommendationMoviesProcessor: ObservableTransformer<GetRecommendationsMoviesAction, GetRecommendationMoviesResult> =
        ObservableTransformer { actions ->
            actions.flatMap {
                movieUseCase.getRecommendationMovies(it.movieId)
                    .map { movies -> GetRecommendationMoviesResult.Success(mapper.mapToUiModelList(movies)) }
                    .cast(GetRecommendationMoviesResult::class.java)
                    .onErrorReturn(GetRecommendationMoviesResult::Failure)
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .startWith(GetRecommendationMoviesResult.Loading)
                    .toObservable()
            }
        }

    internal var movieDetailsAction =
        ObservableTransformer<MovieDetailsAction, MovieDetailsResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(GetMovieDetailsAction::class.java).compose(getMovieDetailsProcessor),
                    shared.ofType(GetSimilarMoviesAction::class.java).compose(getSimilarMoviesProcessor),
                    shared.ofType(GetRecommendationsMoviesAction::class.java).compose(getRecommendationMoviesProcessor)
                )
                    .mergeWith(
                        // Error for not implemented actions
                        shared.filter { movieDetailsAction ->
                            movieDetailsAction !is GetMovieDetailsAction
                                    && movieDetailsAction !is GetSimilarMoviesAction
                                    && movieDetailsAction !is GetRecommendationsMoviesAction
                        }.flatMap { movieDetailsAction ->
                            Observable.error<MovieDetailsResult>(
                                IllegalArgumentException("Unknown Action type: $movieDetailsAction")
                            )
                        }
                    )
            }
        }
}