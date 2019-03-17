package com.ahmedadel.robustandroid.presentation.mvp.tvlist

import com.ahmedadel.robustandroid.feature.tv.entity.TVEntity
import com.ahmedadel.robustandroid.presentation.mvp.BaseView
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.uimodel.TVUiModel
import io.reactivex.Flowable

/**
 * Created at Tito on 3/17/19
 */

interface TVListContract {

    interface View : BaseView {

        fun showTVs(tvs: List<TVUiModel>)

    }

    interface Presenter {

        fun callTVs(pageNumber: Int)

    }

    interface Model {

        fun getTVs(pageNumber: Int): Flowable<List<TVEntity>?>

    }

}