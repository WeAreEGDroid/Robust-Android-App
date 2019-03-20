package com.ahmedadel.robustandroid.presentation.mvi.tv

import com.ahmedadel.robustandroid.presentation.mvi.*
import com.ahmedadel.robustandroid.presentation.mvi.tv.uimodel.TVUiModel

/**
 * Created at Tito on 3/20/19
 */

sealed class TVDetailsIntent : MviIntent {

    data class GetTVDetailsIntent(
        val tvId: Int
    ) : TVDetailsIntent()

    data class GetSimilarTVsIntent(
        val tvId: Int
    ) : TVDetailsIntent()

    data class GetRecommendationsTVsIntent(
        val tvId: Int
    ) : TVDetailsIntent()

}

sealed class TVDetailsAction : MviAction {

    data class GetTVDetailsAction(
        val tvId: Int
    ) : TVDetailsAction()

    data class GetSimilarTVsAction(
        val tvId: Int
    ) : TVDetailsAction()

    data class GetRecommendationsTVsAction(
        val tvId: Int
    ) : TVDetailsAction()

}

sealed class TVDetailsResult : MviResult {

    sealed class GetTVDetailsResult : TVDetailsResult() {
        object Loading : GetTVDetailsResult()
        data class Success(val tv: TVUiModel) : GetTVDetailsResult()
        data class Failure(val error: Throwable) : GetTVDetailsResult()
    }

    sealed class GetSimilarTVsResult : TVDetailsResult() {
        object Loading : GetSimilarTVsResult()
        data class Success(val tvs: List<TVUiModel>) : GetSimilarTVsResult()
        data class Failure(val error: Throwable) : GetSimilarTVsResult()
    }

    sealed class GetRecommendationTVsResult : TVDetailsResult() {
        object Loading : GetRecommendationTVsResult()
        data class Success(val tvs: List<TVUiModel>) : GetRecommendationTVsResult()
        data class Failure(val error: Throwable) : GetRecommendationTVsResult()
    }

}

data class TVDetailsViewState(
    val getTVDetailsViewState: GetTVDetailsViewState = GetTVDetailsViewState(),
    val getSimilarTVsViewState: GetSimilarTVsViewState = GetSimilarTVsViewState(),
    val getRecommendationTVsViewState: GetRecommendationTVsViewState = GetRecommendationTVsViewState()
) : CompoundViewState<GetTVDetailsViewState, GetSimilarTVsViewState, GetRecommendationTVsViewState>(
    getTVDetailsViewState, getSimilarTVsViewState, getRecommendationTVsViewState
)

data class GetTVDetailsViewState(
    val isLoading: Boolean = true,
    val tv: TVUiModel? = null,
    val error: Throwable? = null
) : MviViewState

data class GetSimilarTVsViewState(
    val isLoading: Boolean = true,
    val tvs: List<TVUiModel>? = emptyList(),
    val error: Throwable? = null
) : MviViewState

data class GetRecommendationTVsViewState(
    val isLoading: Boolean = true,
    val tvs: List<TVUiModel>? = emptyList(),
    val error: Throwable? = null
) : MviViewState