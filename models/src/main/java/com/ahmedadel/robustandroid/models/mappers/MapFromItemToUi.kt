package com.ahmedadel.robustandroid.models.mappers

import com.ahmedadel.robustandroid.models.UiModel

/**
 * Created at Tito on 3/15/19
 *
 * Map from entity use case to ui model.
 */

interface MapFromItemToUi<R, U : UiModel> {
    fun mapToUiModel(model: R): U
    fun mapToUiModelList(model: List<R>): List<U>
}