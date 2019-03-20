package com.ahmedadel.robustandroid.presentation.mvi.tv

import com.ahmedadel.robustandroid.presentation.mvi.MviViewModel
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsAction.*
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsIntent.*
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsResult.*
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created at Tito on 3/20/19
 */

class TVDetailsViewModel
@Inject
constructor(private val processor: TVDetailsProcessor) :
    MviViewModel<TVDetailsIntent, TVDetailsAction, TVDetailsViewState>() {

    private val intentsSubject: PublishSubject<TVDetailsIntent> = PublishSubject.create()
    private val statesObservable: Observable<TVDetailsViewState> = composeStates()

    override fun processIntents(intents: Observable<TVDetailsIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<TVDetailsViewState> = statesObservable

    override fun actionFromIntent(intent: TVDetailsIntent): TVDetailsAction {
        return when (intent) {
            is GetTVDetailsIntent -> GetTVDetailsAction(intent.tvId)
            is GetSimilarTVsIntent -> GetSimilarTVsAction(intent.tvId)
            is GetRecommendationsTVsIntent -> GetRecommendationsTVsAction(intent.tvId)
        }
    }

    private fun composeStates(): Observable<TVDetailsViewState> {
        return intentsSubject.map(this::actionFromIntent)
            .compose(processor.tvDetailsAction)
            .scan(TVDetailsViewState(), newViewState)
            .distinctUntilChanged()
    }

    companion object {

        private val newViewState =
            BiFunction<TVDetailsViewState, TVDetailsResult, TVDetailsViewState> { previousState, result ->
                when (result) {

                    is GetTVDetailsResult -> when (result) {
                        is GetTVDetailsResult.Loading ->
                            previousState.copy(
                                getTVDetailsViewState = GetTVDetailsViewState(
                                    isLoading = true
                                )
                            )
                        is GetTVDetailsResult.Success ->
                            previousState.copy(
                                getTVDetailsViewState = GetTVDetailsViewState(
                                    isLoading = false,
                                    tv = result.tv
                                )
                            )
                        is GetTVDetailsResult.Failure ->
                            previousState.copy(
                                getTVDetailsViewState = GetTVDetailsViewState(
                                    isLoading = false,
                                    error = result.error
                                )
                            )
                    }

                    is GetSimilarTVsResult -> when (result) {
                        is GetSimilarTVsResult.Loading ->
                            previousState.copy(
                                getSimilarTVsViewState = GetSimilarTVsViewState(
                                    isLoading = true
                                )
                            )
                        is GetSimilarTVsResult.Success ->
                            previousState.copy(
                                getSimilarTVsViewState = GetSimilarTVsViewState(
                                    isLoading = false,
                                    tvs = result.tvs
                                )
                            )
                        is GetSimilarTVsResult.Failure ->
                            previousState.copy(
                                getSimilarTVsViewState = GetSimilarTVsViewState(
                                    isLoading = false,
                                    error = result.error
                                )
                            )
                    }

                    is GetRecommendationTVsResult -> when (result) {
                        is GetRecommendationTVsResult.Loading ->
                            previousState.copy(
                                getRecommendationTVsViewState = GetRecommendationTVsViewState(
                                    isLoading = true
                                )
                            )
                        is GetRecommendationTVsResult.Success ->
                            previousState.copy(
                                getRecommendationTVsViewState = GetRecommendationTVsViewState(
                                    isLoading = false,
                                    tvs = result.tvs
                                )
                            )
                        is GetRecommendationTVsResult.Failure ->
                            previousState.copy(
                                getRecommendationTVsViewState = GetRecommendationTVsViewState(
                                    isLoading = false,
                                    error = result.error
                                )
                            )
                    }

                }
            }

    }

}