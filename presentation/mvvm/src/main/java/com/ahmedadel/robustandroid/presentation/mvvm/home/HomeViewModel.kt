package com.ahmedadel.robustandroid.presentation.mvvm.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmedadel.robustandroid.core.di.scheduler.BaseSchedulerProvider
import com.ahmedadel.robustandroid.feature.movie.usecase.MovieUseCase
import com.ahmedadel.robustandroid.feature.person.usecase.PersonUseCase
import com.ahmedadel.robustandroid.feature.tv.usecase.TVUseCase
import com.ahmedadel.robustandroid.presentation.mvvm.home.mapper.MovieMapper
import com.ahmedadel.robustandroid.presentation.mvvm.home.mapper.PersonMapper
import com.ahmedadel.robustandroid.presentation.mvvm.home.mapper.TVMapper
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.MovieUiModel
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.PersonUiModel
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.TVUiModel
import com.ahmedadel.robustandroid.presentation.mvvm.BaseViewModel
import com.ahmedadel.robustandroid.presentation.mvvm.ViewState
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created at Tito on 3/16/19
 */

class HomeViewModel
@Inject
constructor(
    baseSchedulerProvider: BaseSchedulerProvider,
    private val movieUseCase: MovieUseCase,
    private val personUseCase: PersonUseCase,
    private val tvUseCase: TVUseCase,
    private val movieMapper: MovieMapper,
    private val personMapper: PersonMapper,
    private val tvMapper: TVMapper
) : BaseViewModel(baseSchedulerProvider) {

    fun getMovies(pageNumber: Int): LiveData<ViewState<List<MovieUiModel>>> {

        val moviesLiveData = MutableLiveData<ViewState<List<MovieUiModel>>>()

        execute(
            loadingConsumer = Consumer {
                moviesLiveData.postValue(
                    ViewState.loading()
                )
            },
            successConsumer = Consumer { movieItemList ->
                movieItemList?.let {
                    moviesLiveData.postValue(
                        ViewState.success(movieMapper.mapToUiModelList(it))
                    )
                }
            },
            throwableConsumer = Consumer { throwable ->
                moviesLiveData.postValue(
                    ViewState.error(throwable.message)
                )
            },
            useCase = movieUseCase.getMovies(pageNumber)
        )

        return moviesLiveData
    }

    fun getPersons(pageNumber: Int): LiveData<ViewState<List<PersonUiModel>>> {

        val personsLiveData = MutableLiveData<ViewState<List<PersonUiModel>>>()

        execute(
            loadingConsumer = Consumer {
                personsLiveData.postValue(
                    ViewState.loading()
                )
            },
            successConsumer = Consumer { personsItemList ->
                personsItemList?.let {
                    personsLiveData.postValue(
                        ViewState.success(personMapper.mapToUiModelList(it))
                    )
                }
            },
            throwableConsumer = Consumer { throwable ->
                personsLiveData.postValue(
                    ViewState.error(throwable.message)
                )
            },
            useCase = personUseCase.getPersons(pageNumber)
        )

        return personsLiveData
    }

    fun getTVs(pageNumber: Int): LiveData<ViewState<List<TVUiModel>>> {

        val tvsLiveData = MutableLiveData<ViewState<List<TVUiModel>>>()

        execute(
            loadingConsumer = Consumer {
                tvsLiveData.postValue(
                    ViewState.loading()
                )
            },
            successConsumer = Consumer { tvItemList ->
                tvItemList?.let {
                    tvsLiveData.postValue(
                        ViewState.success(tvMapper.mapToUiModelList(it))
                    )
                }
            },
            throwableConsumer = Consumer { throwable ->
                tvsLiveData.postValue(
                    ViewState.error(throwable.message)
                )
            },
            useCase = tvUseCase.getTVs(pageNumber)
        )

        return tvsLiveData
    }

}