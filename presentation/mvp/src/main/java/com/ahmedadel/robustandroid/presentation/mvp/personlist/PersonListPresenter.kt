package com.ahmedadel.robustandroid.presentation.mvp.personlist

import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import com.ahmedadel.robustandroid.presentation.mvp.BasePresenter
import com.ahmedadel.robustandroid.presentation.mvp.personlist.mapper.PersonMapper
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created at Tito on 3/17/19
 */

class PersonListPresenter
@Inject
constructor(
    baseSchedulerProvider: BaseSchedulerProvider,
    private val model: PersonListContract.Model,
    private val mapper: PersonMapper
) : BasePresenter<PersonListContract.View>(baseSchedulerProvider),
    PersonListContract.Presenter {

    override fun callPersons(pageNumber: Int) {

        execute(
            loadingConsumer = Consumer {
                getView()?.showLoading(true, pageNumber == 1)
            },
            successConsumer = Consumer { personItemList ->
                getView()?.showLoading(false, pageNumber == 1)
                personItemList?.let {
                    getView()?.showPersons(mapper.mapToUiModelList(it))
                }
            },
            throwableConsumer = Consumer { throwable ->
                getView()?.showErrorMessage(throwable.message)
            },
            useCase = model.getPersons(pageNumber)
        )

    }

}