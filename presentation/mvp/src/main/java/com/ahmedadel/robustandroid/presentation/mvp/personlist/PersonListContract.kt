package com.ahmedadel.robustandroid.presentation.mvp.personlist

import com.ahmedadel.robustandroid.feature.person.entity.PersonEntity
import com.ahmedadel.robustandroid.presentation.mvp.BaseView
import com.ahmedadel.robustandroid.presentation.mvp.personlist.uimodel.PersonUiModel
import io.reactivex.Flowable

/**
 * Created at Tito on 3/17/19
 */

interface PersonListContract {

    interface View : BaseView {

        fun showPersons(persons: List<PersonUiModel>)

    }

    interface Presenter {

        fun callPersons(pageNumber: Int)

    }

    interface Model {

        fun getPersons(pageNumber: Int): Flowable<List<PersonEntity>?>

    }

}