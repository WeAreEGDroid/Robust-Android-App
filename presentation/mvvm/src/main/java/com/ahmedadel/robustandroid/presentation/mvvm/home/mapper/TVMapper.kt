package com.ahmedadel.robustandroid.presentation.mvvm.home.mapper

import com.ahmedadel.robustandroid.feature.tv.entity.TVEntity
import com.ahmedadel.robustandroid.models.mappers.MapFromEntityToUi
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.TVUiModel

/**
 * Created at Tito on 3/16/19
 */

class TVMapper : MapFromEntityToUi<TVEntity, TVUiModel> {

    override fun mapToUiModel(model: TVEntity): TVUiModel {
        with(model) {
            return TVUiModel(
                id,
                name,
                overview,
                originalName,
                originalLanguage,
                posterPath,
                voteCount
            )
        }
    }

    override fun mapToUiModelList(model: List<TVEntity>): List<TVUiModel> {
        return model.map {
            with(it) {
                TVUiModel(
                    id,
                    name,
                    overview,
                    originalName,
                    originalLanguage,
                    posterPath,
                    voteCount
                )
            }
        }
    }

}