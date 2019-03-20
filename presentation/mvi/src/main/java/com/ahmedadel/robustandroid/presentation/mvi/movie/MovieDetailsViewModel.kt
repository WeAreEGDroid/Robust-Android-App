package com.ahmedadel.robustandroid.presentation.mvi.movie

import com.ahmedadel.robustandroid.presentation.mvi.MviViewModel
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsAction.*
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsIntent.*
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsResult.*
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created at Tito on 3/19/19
 */

class MovieDetailsViewModel
@Inject
constructor(private val processor: MovieDetailsProcessor) :
    MviViewModel<MovieDetailsIntent, MovieDetailsAction, MovieDetailsViewState>() {

    private val intentsSubject: PublishSubject<MovieDetailsIntent> = PublishSubject.create()
    private val statesObservable: Observable<MovieDetailsViewState> = composeStates()

    override fun processIntents(intents: Observable<MovieDetailsIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<MovieDetailsViewState> = statesObservable

    override fun actionFromIntent(intent: MovieDetailsIntent): MovieDetailsAction {
        return when (intent) {
            is GetMovieDetailsIntent -> GetMovieDetailsAction(intent.movieId)
            is GetSimilarMoviesIntent -> GetSimilarMoviesAction(intent.movieId)
            is GetRecommendationsMoviesIntent -> GetRecommendationsMoviesAction(intent.movieId)
        }
    }

    private fun composeStates(): Observable<MovieDetailsViewState> {
        return intentsSubject.map(this::actionFromIntent)
            .compose(processor.movieDetailsAction)
            .scan(MovieDetailsViewState(), newViewState)
            .distinctUntilChanged()
    }

    companion object {

        private val newViewState =
            BiFunction<MovieDetailsViewState, MovieDetailsResult, MovieDetailsViewState> { previousState, result ->
                when (result) {

                    is GetMovieDetailsResult -> when (result) {
                        is GetMovieDetailsResult.Loading ->
                            previousState.copy(
                                getMovieDetailsViewState = GetMovieDetailsViewState(
                                    isLoading = true
                                )
                            )
                        is GetMovieDetailsResult.Success ->
                            previousState.copy(
                                getMovieDetailsViewState = GetMovieDetailsViewState(
                                    isLoading = false,
                                    movie = result.movie
                                )
                            )
                        is GetMovieDetailsResult.Failure ->
                            previousState.copy(
                                getMovieDetailsViewState = GetMovieDetailsViewState(
                                    isLoading = false,
                                    error = result.error
                                )
                            )
                    }

                    is GetSimilarMoviesResult -> when (result) {
                        is GetSimilarMoviesResult.Loading ->
                            previousState.copy(
                                getSimilarMoviesViewState = GetSimilarMoviesViewState(
                                    isLoading = true
                                )
                            )
                        is GetSimilarMoviesResult.Success ->
                            previousState.copy(
                                getSimilarMoviesViewState = GetSimilarMoviesViewState(
                                    isLoading = false,
                                    movies = result.movies
                                )
                            )
                        is GetSimilarMoviesResult.Failure ->
                            previousState.copy(
                                getSimilarMoviesViewState = GetSimilarMoviesViewState(
                                    isLoading = false,
                                    error = result.error
                                )
                            )
                    }

                    is GetRecommendationMoviesResult -> when (result) {
                        is GetRecommendationMoviesResult.Loading ->
                            previousState.copy(
                                getRecommendationMoviesViewState = GetRecommendationMoviesViewState(
                                    isLoading = true
                                )
                            )
                        is GetRecommendationMoviesResult.Success ->
                            previousState.copy(
                                getRecommendationMoviesViewState = GetRecommendationMoviesViewState(
                                    isLoading = false,
                                    movies = result.movies
                                )
                            )
                        is GetRecommendationMoviesResult.Failure ->
                            previousState.copy(
                                getRecommendationMoviesViewState = GetRecommendationMoviesViewState(
                                    isLoading = false,
                                    error = result.error
                                )
                            )
                    }

                }
            }

    }

}