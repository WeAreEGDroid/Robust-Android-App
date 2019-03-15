package com.ahmedadel.robustandroid.feature.tv.usecase

import com.ahmedadel.robustandroid.feature.tv.repository.TVRepository
import javax.inject.Inject

/**
 * Created at Tito on 3/15/19
 */

class TVUseCase
@Inject
constructor(private val repository: TVRepository) {

    fun getTVs(pageNumber: Int) = repository.getTVs(pageNumber)

    fun getTV(tvId: Int) = repository.getTV(tvId)

}