package com.ahmedadel.robustandroid.presentation.mvi.tv

import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import com.ahmedadel.robustandroid.feature.tv.usecase.TVUseCase
import com.ahmedadel.robustandroid.presentation.mvi.MviProcessor
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsAction.*
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsResult.*
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsResult.GetTVDetailsResult.*
import com.ahmedadel.robustandroid.presentation.mvi.tv.mapper.TVMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created at Tito on 3/20/19
 */

class TVDetailsProcessor
@Inject
constructor(
    baseSchedulerProvider: BaseSchedulerProvider,
    private val tvUseCase: TVUseCase,
    private val mapper: TVMapper
) : MviProcessor<TVDetailsAction>(baseSchedulerProvider) {

    private val getTVDetailsProcessor: ObservableTransformer<GetTVDetailsAction, GetTVDetailsResult> =
        ObservableTransformer { actions ->
            actions.flatMap {
                tvUseCase.getTV(it.tvId)
                    .map { tv -> Success(mapper.mapToUiModel(tv)) }
                    .cast(GetTVDetailsResult::class.java)
                    .onErrorReturn(GetTVDetailsResult::Failure)
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .startWith(Loading)
                    .toObservable()
            }
        }

    private val getSimilarTVsProcessor: ObservableTransformer<GetSimilarTVsAction, GetSimilarTVsResult> =
        ObservableTransformer { actions ->
            actions.flatMap {
                tvUseCase.getSimilarTVs(it.tvId)
                    .map { tvs -> GetSimilarTVsResult.Success(mapper.mapToUiModelList(tvs)) }
                    .cast(GetSimilarTVsResult::class.java)
                    .onErrorReturn(GetSimilarTVsResult::Failure)
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .startWith(GetSimilarTVsResult.Loading)
                    .toObservable()
            }
        }

    private val getRecommendationTVsProcessor: ObservableTransformer<GetRecommendationsTVsAction, GetRecommendationTVsResult> =
        ObservableTransformer { actions ->
            actions.flatMap {
                tvUseCase.getRecommendationTVs(it.tvId)
                    .map { tvs -> GetRecommendationTVsResult.Success(mapper.mapToUiModelList(tvs)) }
                    .cast(GetRecommendationTVsResult::class.java)
                    .onErrorReturn(GetRecommendationTVsResult::Failure)
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .startWith(GetRecommendationTVsResult.Loading)
                    .toObservable()
            }
        }

    internal var tvDetailsAction =
        ObservableTransformer<TVDetailsAction, TVDetailsResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(GetTVDetailsAction::class.java).compose(getTVDetailsProcessor),
                    shared.ofType(GetSimilarTVsAction::class.java).compose(getSimilarTVsProcessor),
                    shared.ofType(GetRecommendationsTVsAction::class.java).compose(getRecommendationTVsProcessor)
                )
                    .mergeWith(
                        // Error for not implemented actions
                        shared.filter { TVDetailsAction ->
                            TVDetailsAction !is GetTVDetailsAction
                                    && TVDetailsAction !is GetSimilarTVsAction
                                    && TVDetailsAction !is GetRecommendationsTVsAction
                        }.flatMap { TVDetailsAction ->
                            Observable.error<TVDetailsResult>(
                                IllegalArgumentException("Unknown Action type: $TVDetailsAction")
                            )
                        }
                    )
            }
        }
}