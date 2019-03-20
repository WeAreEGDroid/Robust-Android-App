package com.ahmedadel.robustandroid.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ahmedadel.robustandroid.BaseActivity
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.moviedetails.di.MovieDetailsActivityComponentWrapper
import com.ahmedadel.robustandroid.presentation.mvi.MviView
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsIntent
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsViewModel
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsViewState
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity(), MviView<MovieDetailsIntent, MovieDetailsViewState> {

    @Inject
    lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val getMovieIntentPublisher =
        PublishSubject.create<MovieDetailsIntent.GetMovieDetailsIntent>()
    private val getSimilarMoviesIntentPublisher =
        PublishSubject.create<MovieDetailsIntent.GetSimilarMoviesIntent>()
    private val getRecommendationsMoviesIntentPublisher =
        PublishSubject.create<MovieDetailsIntent.GetRecommendationsMoviesIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.getIntExtra(MOVIE_ID_EXTRA, -1)

        configViewModel()

        bind()

        if (movieId != -1) {
            getMovieIntentPublisher.onNext(MovieDetailsIntent.GetMovieDetailsIntent(movieId))
            getSimilarMoviesIntentPublisher.onNext(MovieDetailsIntent.GetSimilarMoviesIntent(movieId))
            getRecommendationsMoviesIntentPublisher.onNext(MovieDetailsIntent.GetRecommendationsMoviesIntent(movieId))
        }
    }

    override val screenName: String
        get() = this.localClassName

    override fun setupActivityComponent() {
        MovieDetailsActivityComponentWrapper.buildComponent(this)
    }

    private fun configViewModel() {
        movieDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }

    override fun bind() {
        movieDetailsViewModel.disposables.add(movieDetailsViewModel.states().subscribe(this::render))
        movieDetailsViewModel.processIntents(intents())
    }

    @Suppress("UNCHECKED_CAST")
    override fun intents(): Observable<MovieDetailsIntent> {
        return Observable.merge(
            getMovieIntentPublisher,
            getSimilarMoviesIntentPublisher,
            getRecommendationsMoviesIntentPublisher
        )
    }

    override fun render(state: MovieDetailsViewState) {
        with(state.getMovieDetailsViewState) {
            Log.e("MovieState", isLoading.toString())
        }

        with(state.getSimilarMoviesViewState) {
            Log.e("SimilarState", isLoading.toString())
        }

        with(state.getRecommendationMoviesViewState) {
            Log.e("RecommendationState", isLoading.toString())
        }
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
