package com.ahmedadel.robustandroid.presentation.mvi.movie

import com.ahmedadel.robustandroid.presentation.mvi.MviAction
import com.ahmedadel.robustandroid.presentation.mvi.MviIntent
import com.ahmedadel.robustandroid.presentation.mvi.MviResult
import com.ahmedadel.robustandroid.presentation.mvi.MviViewState
import com.ahmedadel.robustandroid.presentation.mvi.movie.uimodel.MovieUiModel

/**
 * Created at Tito on 3/19/19
 */

sealed class MovieDetailsIntent : MviIntent {

    data class GetMovieDetailsIntent(
        val movieId: Int
    ) : MovieDetailsIntent()

}

sealed class MovieDetailsAction : MviAction {

    data class GetMovieDetailsAction(
        val movieId: Int
    ) : MovieDetailsAction()

}

sealed class MovieDetailsResult : MviResult {

    sealed class GetMovieDetailsResult: MovieDetailsResult() {
        object Loading : GetMovieDetailsResult()
        data class Success(val movie: MovieUiModel) : GetMovieDetailsResult()
        data class Failure(val error: Throwable) : GetMovieDetailsResult()
    }

}

data class MovieDetailsViewState(
    val isLoading: Boolean = false,
    val movie: MovieUiModel? = null,
    val error: Throwable? = null
) : MviViewState