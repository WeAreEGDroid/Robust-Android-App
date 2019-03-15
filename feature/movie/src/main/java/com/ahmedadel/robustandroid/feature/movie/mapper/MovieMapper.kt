package com.ahmedadel.robustandroid.feature.movie.mapper

import com.ahmedadel.robustandroid.feature.movie.entity.MovieEntity
import com.ahmedadel.robustandroid.models.local.movie.MovieLocal
import com.ahmedadel.robustandroid.models.mappers.MapFromRemoteToItem
import com.ahmedadel.robustandroid.models.remote.movie.MovieRemote

/**
 * Created at Tito on 3/15/19
 */

class MovieMapper : MapFromRemoteToItem<MovieRemote, MovieLocal, MovieEntity> {

    override fun mapFromRemoteToItem(model: MovieRemote): MovieEntity {
        with(model) {
            return MovieEntity(
                id = id,
                overview = overview,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                title = title,
                posterPath = posterPath,
                releaseDate = releaseDate,
                popularity = popularity,
                voteAverage = voteAverage,
                isAdult = isAdult,
                voteCount = voteCount
            )
        }
    }

    override fun mapFromLocalToItem(model: MovieLocal): MovieEntity {
        with(model) {
            return MovieEntity(
                id = id,
                overview = overview,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                title = title,
                posterPath = posterPath,
                releaseDate = releaseDate,
                popularity = popularity,
                voteAverage = voteAverage
            )
        }
    }

    override fun mapFromRemoteToLocal(model: MovieRemote): MovieLocal {
        with(model) {
            return MovieLocal(
                id = id,
                overview = overview,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                video = isVideo,
                title = title,
                posterPath = posterPath,
                backdropPath = backdropPath,
                releaseDate = releaseDate,
                popularity = popularity,
                voteAverage = voteAverage,
                adult = isAdult,
                voteCount = voteCount
            )
        }
    }

}