package com.ahmedadel.robustandroid.presentation.mvp.tvlist

import com.ahmedadel.robustandroid.feature.tv.usecase.TVUseCase
import javax.inject.Inject

/**
 * Created at Tito on 3/17/19
 */

class TVListModel
@Inject
constructor(private val tvUseCase: TVUseCase) : TVListContract.Model {

    override fun getTVs(pageNumber: Int) = tvUseCase.getTVs(pageNumber)

}