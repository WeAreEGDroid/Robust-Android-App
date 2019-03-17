package com.ahmedadel.robustandroid.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.ahmedadel.robustandroid.BaseActivity
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.home.adapter.HomeMovieAdapter
import com.ahmedadel.robustandroid.home.adapter.HomePersonAdapter
import com.ahmedadel.robustandroid.home.adapter.HomeTVAdapter
import com.ahmedadel.robustandroid.home.di.HomeActivityComponentWrapper
import com.ahmedadel.robustandroid.presentation.mvvm.home.HomeViewModel
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.MovieUiModel
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.PersonUiModel
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.TVUiModel
import com.ahmedadel.robustandroid.presentation.mvvm.ViewState
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var movieAdapter: HomeMovieAdapter
    @Inject
    lateinit var personAdapter: HomePersonAdapter
    @Inject
    lateinit var tvAdapter: HomeTVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        configViewModel()

        configUI()

        getMovies()
        getPersons()
        getTVs()

    }

    override val screenName: String
        get() = this@HomeActivity.localClassName

    override fun setupActivityComponent() {
        HomeActivityComponentWrapper.buildComponent(this)
    }

    private fun configViewModel() {
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private fun configUI() {

        title = getString(R.string.home_title)

        movie_recycler_view_list.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        person_recycler_view_list.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = personAdapter
        }

        tv_recycler_view_list.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = tvAdapter
        }
    }

    private fun getMovies() {

        fun setVisibility(isLoading: Boolean, errorMessage: String? = null) {
            movie_loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            movie_recycler_view_list.visibility = if (isLoading) View.GONE else View.VISIBLE
            errorMessage?.let {
                movie_error.visibility = if (isLoading) View.GONE else View.VISIBLE
                movie_error.text = it
            }
        }

        homeViewModel.getMovies(1).observe(this,
            Observer<ViewState<List<MovieUiModel>>> { movieViewState ->
                when (movieViewState.status) {
                    ViewState.Status.LOADING -> {
                        setVisibility(isLoading = true)
                    }

                    ViewState.Status.SUCCESS -> {
                        setVisibility(isLoading = false)
                        movieAdapter.submitList(movieViewState.data)
                    }

                    ViewState.Status.ERROR -> {
                        setVisibility(isLoading = false, errorMessage = movieViewState.message)
                    }
                }
            }
        )

    }

    private fun getPersons() {

        fun setVisibility(isLoading: Boolean, errorMessage: String? = null) {
            person_loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            person_recycler_view_list.visibility = if (isLoading) View.GONE else View.VISIBLE
            errorMessage?.let {
                person_error.visibility = if (isLoading) View.GONE else View.VISIBLE
                person_error.text = it
            }
        }

        homeViewModel.getPersons(1).observe(this,
            Observer<ViewState<List<PersonUiModel>>> { personViewState ->
                when (personViewState.status) {
                    ViewState.Status.LOADING -> {
                        setVisibility(isLoading = true)
                    }

                    ViewState.Status.SUCCESS -> {
                        setVisibility(isLoading = false)
                        personAdapter.submitList(personViewState.data)
                    }

                    ViewState.Status.ERROR -> {
                        setVisibility(isLoading = false, errorMessage = personViewState.message)
                    }
                }
            }
        )

    }

    private fun getTVs() {

        fun setVisibility(isLoading: Boolean, errorMessage: String? = null) {
            tv_loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            tv_recycler_view_list.visibility = if (isLoading) View.GONE else View.VISIBLE
            errorMessage?.let {
                tv_error.visibility = if (isLoading) View.GONE else View.VISIBLE
                tv_error.text = it
            }
        }

        homeViewModel.getTVs(1).observe(this,
            Observer<ViewState<List<TVUiModel>>> { tvViewState ->
                when (tvViewState.status) {
                    ViewState.Status.LOADING -> {
                        setVisibility(isLoading = true)
                    }

                    ViewState.Status.SUCCESS -> {
                        setVisibility(isLoading = false)
                        tvAdapter.submitList(tvViewState.data)
                    }

                    ViewState.Status.ERROR -> {
                        setVisibility(isLoading = false, errorMessage = tvViewState.message)
                    }
                }
            }
        )

    }
}
