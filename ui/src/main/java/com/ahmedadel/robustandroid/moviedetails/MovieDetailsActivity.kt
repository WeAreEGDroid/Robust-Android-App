package com.ahmedadel.robustandroid.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ahmedadel.robustandroid.BaseActivity
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.moviedetails.di.MovieDetailsActivityComponentWrapper
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsIntent
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsViewModel
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsViewState
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity() {

    @Inject
    lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.getIntExtra(MOVIE_ID_EXTRA, -1)

        configViewModel()

        if (movieId != -1) {
            movieDetailsViewModel.states().observe(this, Observer<MovieDetailsViewState> { viewState ->
                renderViewState(viewState)
            })
            movieDetailsViewModel.processIntent(MovieDetailsIntent.GetMovieDetailsIntent(movieId))
        }
    }

    private fun renderViewState(viewState: MovieDetailsViewState) {
        Toast.makeText(this@MovieDetailsActivity, viewState.movie?.originalTitle ?: "title", Toast.LENGTH_SHORT).show()
    }

    override val screenName: String
        get() = this.localClassName

    override fun setupActivityComponent() {
        MovieDetailsActivityComponentWrapper.buildComponent(this)
    }

    private fun configViewModel() {
        movieDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }

    companion object {

        const val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"

        fun start(context: Context, movieId: Int) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_ID_EXTRA, movieId)
            context.startActivity(intent)
        }

    }

}
