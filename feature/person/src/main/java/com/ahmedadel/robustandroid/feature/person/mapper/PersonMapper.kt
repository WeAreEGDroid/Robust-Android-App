package com.ahmedadel.robustandroid.feature.person.mapper

import com.ahmedadel.robustandroid.feature.person.entity.PersonEntity
import com.ahmedadel.robustandroid.models.local.person.PersonLocal
import com.ahmedadel.robustandroid.models.mappers.MapFromRemoteToItem
import com.ahmedadel.robustandroid.models.remote.person.PersonRemote

/**
 * Created at Tito on 3/15/19
 */

class PersonMapper : MapFromRemoteToItem<PersonRemote, PersonLocal, PersonEntity> {

    override fun mapFromRemoteToItem(model: PersonRemote): PersonEntity {
        with(model) {
            return PersonEntity(
                id = id,
                popularity = popularity,
                name = name,
                profilePath = profilePath,
                isAdult = isAdult
            )
        }
    }

    override fun mapFromLocalToItem(model: PersonLocal): PersonEntity {
        with(model) {
            return PersonEntity(
                id = id,
                popularity = popularity,
                name = name,
                profilePath = profilePath,
                isAdult = adult
            )
        }
    }

    override fun mapFromRemoteToLocal(model: PersonRemote): PersonLocal {
        with(model) {
            return PersonLocal(
                id = id,
                popularity = popularity,
                name = name,
                profilePath = profilePath,
                adult = isAdult
            )
        }
    }

}