package com.ahmedadel.robustandroid.movielist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedadel.robustandroid.BaseActivity
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.movielist.adapter.MovieListAdapter
import com.ahmedadel.robustandroid.movielist.di.MovieListActivityComponentWrapper
import com.ahmedadel.robustandroid.presentation.mvp.movielist.MovieListContract
import com.ahmedadel.robustandroid.presentation.mvp.movielist.MovieListPresenter
import com.ahmedadel.robustandroid.presentation.mvp.movielist.uimodel.MovieUiModel
import com.ahmedadel.robustandroid.widget.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject

class MovieListActivity : BaseActivity(), MovieListContract.View {

    @Inject
    lateinit var presenter: MovieListPresenter

    @Inject
    lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        presenter.setView(this)

        configUI()

        getMovies()

    }

    override val screenName: String
        get() = this.localClassName

    override fun setupActivityComponent() {
        MovieListActivityComponentWrapper.buildComponent(this)
    }

    private fun configUI() {

        title = getString(R.string.movie_list_title)

        movie_list_view.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = movieListAdapter
        }

        movie_list_swipe_refresh_layout.setOnRefreshListener {
            getMovies()
        }

        movie_list_view.addOnScrollListener(object : EndlessRecyclerViewScrollListener(
            movie_list_view.layoutManager as LinearLayoutManager
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                getMovies(page)
            }
        })

    }

    private fun getMovies(pageNumber: Int = 1) {
        if (pageNumber == 1)
            movieListAdapter.clear()
        presenter.callMovies(pageNumber)
    }

    override fun showLoading(isLoading: Boolean, isFirstPage: Boolean) {
        if (isFirstPage) {
            movie_list_swipe_refresh_layout.post {
                movie_list_swipe_refresh_layout.isRefreshing = isLoading
            }
        } else {
            movie_list_load_more_progress_bar.post {
                movie_list_load_more_progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    override fun showMovies(movies: List<MovieUiModel>) {
        movie_list_load_more_progress_bar.visibility = View.GONE
        empty_list.visibility = View.GONE
        movie_list_view.visibility = View.VISIBLE

        movieListAdapter.submitList(movies)
    }

    override fun showErrorMessage(message: String?) {
        movie_list_load_more_progress_bar.visibility = View.GONE
        empty_list.visibility = View.VISIBLE
        movie_list_view.visibility = View.GONE

        empty_list.text = message
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clear()
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MovieListActivity::class.java)
            context.startActivity(intent)
        }

    }

}
