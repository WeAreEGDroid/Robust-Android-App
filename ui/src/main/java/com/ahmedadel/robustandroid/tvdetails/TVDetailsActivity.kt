package com.ahmedadel.robustandroid.tvdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ahmedadel.robustandroid.BaseActivity
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.presentation.mvi.MviView
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsIntent
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsViewModel
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsViewState
import com.ahmedadel.robustandroid.tvdetails.di.TVDetailsActivityComponentWrapper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class TVDetailsActivity : BaseActivity(), MviView<TVDetailsIntent, TVDetailsViewState> {

    @Inject
    lateinit var tvDetailsViewModel: TVDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val getTVIntentPublisher =
        PublishSubject.create<TVDetailsIntent.GetTVDetailsIntent>()
    private val getSimilarTVsIntentPublisher =
        PublishSubject.create<TVDetailsIntent.GetSimilarTVsIntent>()
    private val getRecommendationsTVsIntentPublisher =
        PublishSubject.create<TVDetailsIntent.GetRecommendationsTVsIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_details)

        val tvId = intent.getIntExtra(TV_ID_EXTRA, -1)

        configViewModel()

        bind()

        if (tvId != -1) {
            getTVIntentPublisher.onNext(TVDetailsIntent.GetTVDetailsIntent(tvId))
            getSimilarTVsIntentPublisher.onNext(TVDetailsIntent.GetSimilarTVsIntent(tvId))
            getRecommendationsTVsIntentPublisher.onNext(TVDetailsIntent.GetRecommendationsTVsIntent(tvId))
        }
    }

    override val screenName: String
        get() = this.localClassName

    override fun setupActivityComponent() {
        TVDetailsActivityComponentWrapper.buildComponent(this)
    }

    private fun configViewModel() {
        tvDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(TVDetailsViewModel::class.java)
    }

    override fun bind() {
        tvDetailsViewModel.disposables.add(tvDetailsViewModel.states().subscribe(this::render))
        tvDetailsViewModel.processIntents(intents())
    }

    @Suppress("UNCHECKED_CAST")
    override fun intents(): Observable<TVDetailsIntent> {
        return Observable.merge(
            getTVIntentPublisher,
            getSimilarTVsIntentPublisher,
            getRecommendationsTVsIntentPublisher
        )
    }

    override fun render(state: TVDetailsViewState) {
        with(state.getTVDetailsViewState) {
            Log.e("TVState", isLoading.toString())
        }

        with(state.getSimilarTVsViewState) {
            Log.e("SimilarState", isLoading.toString())
        }

        with(state.getRecommendationTVsViewState) {
            Log.e("RecommendationState", isLoading.toString())
        }
    }

    companion object {

        const val TV_ID_EXTRA = "TV_ID_EXTRA"

        fun start(context: Context, tvId: Int) {
            val intent = Intent(context, TVDetailsActivity::class.java)
            intent.putExtra(TV_ID_EXTRA, tvId)
            context.startActivity(intent)
        }

    }
}
