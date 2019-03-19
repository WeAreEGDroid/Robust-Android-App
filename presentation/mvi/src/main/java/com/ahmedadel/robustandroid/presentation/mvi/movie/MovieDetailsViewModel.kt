package com.ahmedadel.robustandroid.presentation.mvi.movie

import com.ahmedadel.robustandroid.presentation.mvi.MviProcessor
import com.ahmedadel.robustandroid.presentation.mvi.MviViewModel
import com.ahmedadel.robustandroid.presentation.mvi.Reducer
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsAction.GetMovieDetailsAction
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsIntent.GetMovieDetailsIntent
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsResult.GetMovieDetailsResult
import javax.inject.Inject

/**
 * Created at Tito on 3/19/19
 */

class MovieDetailsViewModel
@Inject
constructor(private val processor: MovieDetailsProcessor) :
    MviViewModel<MovieDetailsIntent, MovieDetailsAction, MovieDetailsViewState>() {

    private val reducer: Reducer<MovieDetailsViewState, MovieDetailsResult> = { previousState, result ->
        when (result) {
            is GetMovieDetailsResult -> when (result) {
                is GetMovieDetailsResult.Loading ->
                    previousState.copy(isLoading = true)
                is GetMovieDetailsResult.Success ->
                    previousState.copy(isLoading = false, movie = result.movie)
                is GetMovieDetailsResult.Failure ->
                    previousState.copy(isLoading = false, error = result.error)
            }
        }
    }

    init {
        bindActions()
    }

    override fun getProcessor(): MviProcessor<MovieDetailsAction> {
        return processor
    }

    override fun actionFromIntent(intent: MovieDetailsIntent): MovieDetailsAction {
        return when (intent) {
            is GetMovieDetailsIntent -> GetMovieDetailsAction(intent.movieId)
        }
    }

    private fun bindActions() {
        val getMovieDetails = processor.getMovieDetailsProcessor
            .scan(MovieDetailsViewState(), reducer)
            .distinctUntilChanged()
            .subscribe {
                state.postValue(it)
            }
        
        disposables.add(getMovieDetails)
    }

}