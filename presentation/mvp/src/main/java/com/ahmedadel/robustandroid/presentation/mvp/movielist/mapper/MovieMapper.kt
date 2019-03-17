package com.ahmedadel.robustandroid.presentation.mvp.movielist.mapper

import com.ahmedadel.robustandroid.feature.movie.entity.MovieEntity
import com.ahmedadel.robustandroid.models.mappers.MapFromEntityToUi
import com.ahmedadel.robustandroid.presentation.mvp.movielist.uimodel.MovieUiModel

/**
 * Created at Tito on 3/17/19
 */

class MovieMapper : MapFromEntityToUi<MovieEntity, MovieUiModel> {

    override fun mapToUiModel(model: MovieEntity): MovieUiModel {
        with(model) {
            return MovieUiModel(
                id = id,
                title = title,
                overview = overview,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                posterPath = posterPath,
                releaseDate = releaseDate,
                voteAverage = voteAverage,
                isAdult = isAdult
            )
        }
    }

    override fun mapToUiModelList(model: List<MovieEntity>): List<MovieUiModel> {
        return model.map {
            with(it) {
                MovieUiModel(
                    id = id,
                    title = title,
                    overview = overview,
                    originalLanguage = originalLanguage,
                    originalTitle = originalTitle,
                    posterPath = posterPath,
                    releaseDate = releaseDate,
                    voteAverage = voteAverage,
                    isAdult = isAdult
                )
            }
        }
    }

}