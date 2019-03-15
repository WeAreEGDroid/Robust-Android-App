package com.ahmedadel.robustandroid.feature.tv.mapper

import com.ahmedadel.robustandroid.feature.tv.entity.TVEntity
import com.ahmedadel.robustandroid.models.local.tv.TVLocal
import com.ahmedadel.robustandroid.models.mappers.MapFromRemoteToEntity
import com.ahmedadel.robustandroid.models.remote.tv.TVRemote

/**
 * Created at Tito on 3/15/19
 */

class TVMapper : MapFromRemoteToEntity<TVRemote, TVLocal, TVEntity> {

    override fun mapFromRemoteToEntity(model: TVRemote): TVEntity {
        with(model) {
            return TVEntity(
                id = id,
                overview = overview,
                originalLanguage = originalLanguage,
                posterPath = posterPath,
                voteAverage = voteAverage,
                originalName = originalName,
                name = name,
                voteCount = voteCount
            )
        }
    }

    override fun mapFromLocalToEntity(model: TVLocal): TVEntity {
        with(model) {
            return TVEntity(
                id = id,
                overview = overview,
                originalLanguage = originalLanguage,
                posterPath = posterPath,
                voteAverage = voteAverage,
                originalName = originalName,
                name = name,
                voteCount = voteCount
            )
        }
    }

    override fun mapFromRemoteToLocal(model: TVRemote): TVLocal {
        with(model) {
            return TVLocal(
                id = id,
                firstAirDate = firstAirDate,
                overview = overview,
                originalLanguage = originalLanguage,
                posterPath = posterPath,
                backdropPath = backdropPath,
                popularity = popularity,
                voteAverage = voteAverage,
                originalName = originalName,
                name = name,
                voteCount = voteCount
            )
        }
    }

}