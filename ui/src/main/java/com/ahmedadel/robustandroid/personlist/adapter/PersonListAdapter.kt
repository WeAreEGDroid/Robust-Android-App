package com.ahmedadel.robustandroid.personlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadel.robustandroid.BuildConfig
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.presentation.mvp.personlist.uimodel.PersonUiModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_person_list_item.view.*

/**
 * Created at Tito on 3/18/19
 */

class PersonListAdapter : RecyclerView.Adapter<PersonListAdapter.ViewHolder>() {

    private val persons: MutableList<PersonUiModel> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.activity_person_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(persons[position])
    }

    override fun getItemCount() = persons.size

    fun submitList(newPersons: List<PersonUiModel>) {
        persons.addAll(newPersons)
        val uniquePersonList = persons.distinctBy { movie ->
            movie.id
        }
        persons.clear()
        persons.addAll(uniquePersonList)
        notifyDataSetChanged()
    }

    fun clear() {
        persons.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(person: PersonUiModel) {
            itemView.person_title.text = person.name
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + person.profilePath)
                .into(itemView.person_image)
        }

    }

}