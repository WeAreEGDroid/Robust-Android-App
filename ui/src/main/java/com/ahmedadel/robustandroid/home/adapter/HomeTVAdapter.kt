package com.ahmedadel.robustandroid.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadel.robustandroid.BuildConfig
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.presentation.mvvm.home.uimodel.TVUiModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.tv_list_item.view.*

class HomeTVAdapter : RecyclerView.Adapter<HomeTVAdapter.ViewHolder>() {

    private val tvs: MutableList<TVUiModel> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.tv_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(tvs[position])
    }

    override fun getItemCount() = tvs.size

    fun submitList(newTVs: List<TVUiModel>?) {
        newTVs?.let {
            tvs.addAll(it)
            val uniqueTVList = tvs.distinctBy { tv ->
                tv.id
            }
            tvs.clear()
            tvs.addAll(uniqueTVList)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tv: TVUiModel) {
            itemView.tv_title.text = tv.name
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + tv.posterPath)
                .into(itemView.tv_image)
        }

    }


}
