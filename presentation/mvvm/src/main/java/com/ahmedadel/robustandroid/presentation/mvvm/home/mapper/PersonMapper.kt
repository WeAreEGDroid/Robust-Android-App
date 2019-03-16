package com.ahmedadel.robustandroid.presentation.mvvm.home.mapper

import com.ahmedadel.robustandroid.feature.person.entity.PersonEntity
import com.ahmedadel.robustandroid.models.mappers.MapFromEntityToUi
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.PersonUiModel

/**
 * Created at Tito on 3/16/19
 */

class PersonMapper : MapFromEntityToUi<PersonEntity, PersonUiModel> {

    override fun mapToUiModel(model: PersonEntity): PersonUiModel {
        with(model) {
            return PersonUiModel(
                id = id,
                popularity = popularity,
                name = name,
                profilePath = profilePath,
                isAdult = isAdult
            )
        }
    }

    override fun mapToUiModelList(model: List<PersonEntity>): List<PersonUiModel> {
        return model.map {
            with(it) {
                PersonUiModel(
                    id = id,
                    popularity = popularity,
                    name = name,
                    profilePath = profilePath,
                    isAdult = isAdult
                )
            }
        }
    }

}