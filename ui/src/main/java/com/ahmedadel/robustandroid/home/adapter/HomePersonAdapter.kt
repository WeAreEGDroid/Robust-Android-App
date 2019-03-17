package com.ahmedadel.robustandroid.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadel.robustandroid.BuildConfig
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.PersonUiModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.person_list_item.view.*

class HomePersonAdapter : RecyclerView.Adapter<HomePersonAdapter.ViewHolder>() {

    private val persons: MutableList<PersonUiModel> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.person_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(persons[position])
    }

    override fun getItemCount() = persons.size

    fun submitList(newPersons: List<PersonUiModel>?) {
        newPersons?.let {
            persons.addAll(it)
            val uniquePersonList = persons.distinctBy { person ->
                person.id
            }
            persons.clear()
            persons.addAll(uniquePersonList)
            notifyDataSetChanged()
        }
    }

    @Suppress("unused")
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
