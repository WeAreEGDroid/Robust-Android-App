package com.ahmedadel.robustandroid.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadel.robustandroid.BuildConfig
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.TVUiModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.tv_list_item.view.*

class HomeTVAdapter : ListAdapter<TVUiModel, HomeTVAdapter.ViewHolder>(TVDiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.tv_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(tv: TVUiModel) {
            itemView.tv_title.text = tv.name
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + tv.posterPath)
                .into(itemView.tv_image)
        }

    }

    class TVDiffCallback : DiffUtil.ItemCallback<TVUiModel>() {
        override fun areItemsTheSame(oldItem: TVUiModel, newItem: TVUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TVUiModel, newItem: TVUiModel): Boolean {
            return oldItem == newItem
        }
    }

}
