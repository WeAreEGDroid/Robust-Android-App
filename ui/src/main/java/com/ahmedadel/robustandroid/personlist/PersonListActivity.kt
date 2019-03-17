package com.ahmedadel.robustandroid.personlist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedadel.robustandroid.BaseActivity
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.personlist.adapter.PersonListAdapter
import com.ahmedadel.robustandroid.personlist.di.PersonListActivityComponentWrapper
import com.ahmedadel.robustandroid.presentation.mvp.personlist.PersonListContract
import com.ahmedadel.robustandroid.presentation.mvp.personlist.PersonListPresenter
import com.ahmedadel.robustandroid.presentation.mvp.personlist.uimodel.PersonUiModel
import com.ahmedadel.robustandroid.widget.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_person_list.*
import javax.inject.Inject

class PersonListActivity : BaseActivity(), PersonListContract.View {

    @Inject
    lateinit var presenter: PersonListPresenter

    @Inject
    lateinit var personListAdapter: PersonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)

        buildNavigateUpArrow()

        presenter.setView(this)

        configUI()

        getPersons()

    }

    override val screenName: String
        get() = this.localClassName

    override fun setupActivityComponent() {
        PersonListActivityComponentWrapper.buildComponent(this)
    }

    private fun configUI() {

        title = getString(R.string.person_list_title)

        person_list_view.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = personListAdapter
        }

        person_list_swipe_refresh_layout.setOnRefreshListener {
            getPersons()
        }

        person_list_view.addOnScrollListener(object : EndlessRecyclerViewScrollListener(
            person_list_view.layoutManager as LinearLayoutManager
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                getPersons(page)
            }
        })

    }

    private fun getPersons(pageNumber: Int = 1) {
        if (pageNumber == 1)
            personListAdapter.clear()
        presenter.callPersons(pageNumber)
    }

    override fun showLoading(isLoading: Boolean, isFirstPage: Boolean) {
        if (isFirstPage) {
            person_list_swipe_refresh_layout.post {
                person_list_swipe_refresh_layout.isRefreshing = isLoading
            }
        } else {
            person_list_load_more_progress_bar.post {
                person_list_load_more_progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    override fun showPersons(persons: List<PersonUiModel>) {
        person_list_load_more_progress_bar.visibility = View.GONE
        empty_list.visibility = View.GONE
        person_list_view.visibility = View.VISIBLE

        personListAdapter.submitList(persons)
    }

    override fun showErrorMessage(message: String?) {
        person_list_load_more_progress_bar.visibility = View.GONE
        empty_list.visibility = View.VISIBLE
        person_list_view.visibility = View.GONE

        empty_list.text = message
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clear()
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, PersonListActivity::class.java)
            context.startActivity(intent)
        }

    }

}
