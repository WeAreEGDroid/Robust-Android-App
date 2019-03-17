package com.ahmedadel.robustandroid.presentation.mvp.personlist

import com.ahmedadel.robustandroid.feature.person.usecase.PersonUseCase
import javax.inject.Inject

/**
 * Created at Tito on 3/17/19
 */

class PersonListModel
@Inject
constructor(private val personUseCase: PersonUseCase) : PersonListContract.Model {

    override fun getPersons(pageNumber: Int) = personUseCase.getPersons(pageNumber)

}