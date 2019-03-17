package com.ahmedadel.robustandroid.presentation.mvp.tvlist

import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import com.ahmedadel.robustandroid.presentation.mvp.BasePresenter
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.mapper.TVMapper
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created at Tito on 3/17/19
 */

class TVListPresenter
@Inject
constructor(
    baseSchedulerProvider: BaseSchedulerProvider,
    private val model: TVListContract.Model,
    private val mapper: TVMapper
) : BasePresenter<TVListContract.View>(baseSchedulerProvider),
    TVListContract.Presenter {

    override fun callTVs(pageNumber: Int) {

        execute(
            loadingConsumer = Consumer {
                getView()?.showLoading(true, pageNumber == 1)
            },
            successConsumer = Consumer { tvItemList ->
                getView()?.showLoading(false, pageNumber == 1)
                tvItemList?.let {
                    getView()?.showTVs(mapper.mapToUiModelList(it))
                }
            },
            throwableConsumer = Consumer { throwable ->
                getView()?.showErrorMessage(throwable.message)
            },
            useCase = model.getTVs(pageNumber)
        )

    }

}