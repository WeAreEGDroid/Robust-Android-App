package com.ahmedadel.robustandroid.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.ahmedadel.robustandroid.BaseActivity
import com.ahmedadel.robustandroid.BuildConfig
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.moviedetails.adapter.RecommendationMoviesAdapter
import com.ahmedadel.robustandroid.moviedetails.adapter.SimilarMoviesAdapter
import com.ahmedadel.robustandroid.moviedetails.di.MovieDetailsActivityComponentWrapper
import com.ahmedadel.robustandroid.presentation.mvi.MviView
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsIntent
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsViewModel
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsViewState
import com.ahmedadel.robustandroid.presentation.mvi.movie.uimodel.MovieUiModel
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity(), MviView<MovieDetailsIntent, MovieDetailsViewState> {

    @Inject
    lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var similarMoviesAdapter: SimilarMoviesAdapter
    @Inject
    lateinit var recommendationMoviesAdapter: RecommendationMoviesAdapter

    private var movie: MovieUiModel? = null

    private val getMovieIntentPublisher =
        PublishSubject.create<MovieDetailsIntent.GetMovieDetailsIntent>()
    private val getSimilarMoviesIntentPublisher =
        PublishSubject.create<MovieDetailsIntent.GetSimilarMoviesIntent>()
    private val getRecommendationsMoviesIntentPublisher =
        PublishSubject.create<MovieDetailsIntent.GetRecommendationsMoviesIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        buildNavigateUpArrow()

        val movieId = intent.getIntExtra(MOVIE_ID_EXTRA, -1)

        configViewModel()

        configUI()

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

    private fun configUI() {

        similar_movies_recycler_view_list.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = similarMoviesAdapter
        }

        recommendation_movies_recycler_view_list.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = recommendationMoviesAdapter
        }

        similarMoviesAdapter.onSimilarMovieClickListener =
            object : SimilarMoviesAdapter.OnSimilarMovieClickListener {
                override fun setOnSimilarMovieClickListener(movieId: Int) {
                    MovieDetailsActivity.start(this@MovieDetailsActivity, movieId)
                }
            }

        recommendationMoviesAdapter.onRecommendationMovieClickListener =
            object : RecommendationMoviesAdapter.OnRecommendationMovieClickListener {
                override fun setRecommendationMovieClickListener(movieId: Int) {
                    MovieDetailsActivity.start(this@MovieDetailsActivity, movieId)
                }
            }

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

        fun setVisibility(state: MovieDetailsViewState) {

            with(state.getMovieDetailsViewState) {
                movie_details_loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                error?.let {
                    movie_details_loading.visibility = View.GONE
                    movie_details_error.visibility = View.VISIBLE
                    movie_details_error.text = it.message
                }
            }

            with(state.getSimilarMoviesViewState) {
                similar_movies_loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                error?.let {
                    similar_movies_loading.visibility = View.GONE
                    similar_movies_error.visibility = View.VISIBLE
                    similar_movies_error.text = it.message
                }
            }

            with(state.getRecommendationMoviesViewState) {
                recommendation_movies_loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                error?.let {
                    recommendation_movies_loading.visibility = View.GONE
                    recommendation_movies_error.visibility = View.VISIBLE
                    recommendation_movies_error.text = it.message
                }
            }
        }

        setVisibility(state)

        with(state.getMovieDetailsViewState) {
            movie?.let {
                movie_details_error.visibility = View.GONE
                setMovieDetails(it)
            }
        }

        with(state.getSimilarMoviesViewState) {
            if (movies?.isEmpty() == true) {
                similarMoviesAdapter.clear()
                similar_movies_error.visibility = View.VISIBLE
            } else {
                similar_movies_error.visibility = View.GONE
                similarMoviesAdapter.submitList(movies)
            }
        }

        with(state.getRecommendationMoviesViewState) {
            if (movies?.isEmpty() == true) {
                recommendationMoviesAdapter.clear()
                recommendation_movies_error.visibility = View.VISIBLE
            } else {
                recommendation_movies_error.visibility = View.GONE
                recommendationMoviesAdapter.submitList(movies)
            }
        }
    }

    private fun setMovieDetails(updatedMovie: MovieUiModel) {
        if (movie != updatedMovie) {
            movie = updatedMovie
            title = updatedMovie.title
            Glide.with(this@MovieDetailsActivity)
                .load(BuildConfig.IMAGE_URL + updatedMovie.posterPath)
                .into(movie_details_image)
            movie_details_title.text = updatedMovie.title
            movie_details_desc.text = updatedMovie.overview
            movie_details_language.text = getString(R.string.language, updatedMovie.originalLanguage)
            movie_details_release_date.text = getString(R.string.release_date, updatedMovie.releaseDate)
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
