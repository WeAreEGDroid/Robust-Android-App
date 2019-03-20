package com.ahmedadel.robustandroid.tvlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedadel.robustandroid.BaseActivity
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.TVListContract
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.TVListPresenter
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.uimodel.TVUiModel
import com.ahmedadel.robustandroid.tvdetails.TVDetailsActivity
import com.ahmedadel.robustandroid.tvlist.adapter.TVListAdapter
import com.ahmedadel.robustandroid.tvlist.di.TVListActivityComponentWrapper
import com.ahmedadel.robustandroid.widget.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_tv_list.*
import javax.inject.Inject

class TVListActivity : BaseActivity(), TVListContract.View {

    @Inject
    lateinit var presenter: TVListPresenter

    @Inject
    lateinit var tvListAdapter: TVListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_list)

        buildNavigateUpArrow()

        presenter.setView(this)

        configUI()

        getTVs()

    }

    override val screenName: String
        get() = this.localClassName

    override fun setupActivityComponent() {
        TVListActivityComponentWrapper.buildComponent(this)
    }

    private fun configUI() {

        title = getString(R.string.tv_list_title)

        tv_list_view.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = tvListAdapter
        }

        tv_list_swipe_refresh_layout.setOnRefreshListener {
            getTVs()
        }

        tv_list_view.addOnScrollListener(object : EndlessRecyclerViewScrollListener(
            tv_list_view.layoutManager as LinearLayoutManager
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                getTVs(page)
            }
        })

        tvListAdapter.onTVItemClickListener = object : TVListAdapter.OnTVItemClickListener {
            override fun setOnTVItemClickListener(tvId: Int) {
                TVDetailsActivity.start(this@TVListActivity, tvId)
            }
        }

    }

    private fun getTVs(pageNumber: Int = 1) {
        if (pageNumber == 1)
            tvListAdapter.clear()
        presenter.callTVs(pageNumber)
    }

    override fun showLoading(isLoading: Boolean, isFirstPage: Boolean) {
        if (isFirstPage) {
            tv_list_swipe_refresh_layout.post {
                tv_list_swipe_refresh_layout.isRefreshing = isLoading
            }
        } else {
            tv_list_load_more_progress_bar.post {
                tv_list_load_more_progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    override fun showTVs(tvs: List<TVUiModel>) {
        tv_list_load_more_progress_bar.visibility = View.GONE
        empty_list.visibility = View.GONE
        tv_list_view.visibility = View.VISIBLE

        tvListAdapter.submitList(tvs)
    }

    override fun showErrorMessage(message: String?) {
        tv_list_load_more_progress_bar.visibility = View.GONE
        empty_list.visibility = View.VISIBLE
        tv_list_view.visibility = View.GONE

        empty_list.text = message
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clear()
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, TVListActivity::class.java)
            context.startActivity(intent)
        }

    }

}
