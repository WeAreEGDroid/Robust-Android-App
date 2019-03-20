package com.ahmedadel.robustandroid.presentation.mvi.movie

import com.ahmedadel.robustandroid.presentation.mvi.*
import com.ahmedadel.robustandroid.presentation.mvi.movie.uimodel.MovieUiModel

/**
 * Created at Tito on 3/19/19
 */

sealed class MovieDetailsIntent : MviIntent {

    data class GetMovieDetailsIntent(
        val movieId: Int
    ) : MovieDetailsIntent()

    data class GetSimilarMoviesIntent(
        val movieId: Int
    ) : MovieDetailsIntent()

    data class GetRecommendationsMoviesIntent(
        val movieId: Int
    ) : MovieDetailsIntent()

}

sealed class MovieDetailsAction : MviAction {

    data class GetMovieDetailsAction(
        val movieId: Int
    ) : MovieDetailsAction()

    data class GetSimilarMoviesAction(
        val movieId: Int
    ) : MovieDetailsAction()

    data class GetRecommendationsMoviesAction(
        val movieId: Int
    ) : MovieDetailsAction()

}

sealed class MovieDetailsResult : MviResult {

    sealed class GetMovieDetailsResult : MovieDetailsResult() {
        object Loading : GetMovieDetailsResult()
        data class Success(val movie: MovieUiModel) : GetMovieDetailsResult()
        data class Failure(val error: Throwable) : GetMovieDetailsResult()
    }

    sealed class GetSimilarMoviesResult : MovieDetailsResult() {
        object Loading : GetSimilarMoviesResult()
        data class Success(val movies: List<MovieUiModel>) : GetSimilarMoviesResult()
        data class Failure(val error: Throwable) : GetSimilarMoviesResult()
    }

    sealed class GetRecommendationMoviesResult : MovieDetailsResult() {
        object Loading : GetRecommendationMoviesResult()
        data class Success(val movies: List<MovieUiModel>) : GetRecommendationMoviesResult()
        data class Failure(val error: Throwable) : GetRecommendationMoviesResult()
    }

}

data class MovieDetailsViewState(
    val getMovieDetailsViewState: GetMovieDetailsViewState = GetMovieDetailsViewState(),
    val getSimilarMoviesViewState: GetSimilarMoviesViewState = GetSimilarMoviesViewState(),
    val getRecommendationMoviesViewState: GetRecommendationMoviesViewState = GetRecommendationMoviesViewState()
) : CompoundViewState<GetMovieDetailsViewState, GetSimilarMoviesViewState, GetRecommendationMoviesViewState>(
    getMovieDetailsViewState, getSimilarMoviesViewState, getRecommendationMoviesViewState
)

data class GetMovieDetailsViewState(
    val isLoading: Boolean = true,
    val movie: MovieUiModel? = null,
    val error: Throwable? = null
) : MviViewState

data class GetSimilarMoviesViewState(
    val isLoading: Boolean = true,
    val movies: List<MovieUiModel>? = emptyList(),
    val error: Throwable? = null
) : MviViewState

data class GetRecommendationMoviesViewState(
    val isLoading: Boolean = true,
    val movies: List<MovieUiModel>? = emptyList(),
    val error: Throwable? = null
) : MviViewState