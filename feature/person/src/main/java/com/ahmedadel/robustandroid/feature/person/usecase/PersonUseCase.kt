package com.ahmedadel.robustandroid.feature.person.usecase

import com.ahmedadel.robustandroid.feature.person.repository.PersonRepository
import javax.inject.Inject

/**
 * Created at Tito on 3/15/19
 */

class PersonUseCase
@Inject
constructor(private val repository: PersonRepository) {

    fun getPersons(pageNumber: Int) = repository.getPersons(pageNumber)

    fun getPerson(personId: Int) = repository.getPerson(personId)

}