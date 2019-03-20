package com.ahmedadel.robustandroid.tvlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadel.robustandroid.BuildConfig
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.presentation.mvp.tvlist.uimodel.TVUiModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tv_list_item.view.*

/**
 * Created at Tito on 3/18/19
 */

class TVListAdapter : RecyclerView.Adapter<TVListAdapter.ViewHolder>() {

    private val tvs: MutableList<TVUiModel> = ArrayList()

    var onTVItemClickListener: OnTVItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_tv_list_item, viewGroup, false),
            onTVItemClickListener
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(tvs[position])
    }

    override fun getItemCount() = tvs.size

    fun submitList(newTVs: List<TVUiModel>) {
        tvs.addAll(newTVs)
        val uniqueTVList = tvs.distinctBy { tv ->
            tv.id
        }
        tvs.clear()
        tvs.addAll(uniqueTVList)
        notifyDataSetChanged()
    }

    fun clear() {
        tvs.clear()
        notifyDataSetChanged()
    }

    interface OnTVItemClickListener {
        fun setOnTVItemClickListener(tvId: Int)
    }

    class ViewHolder(itemView: View, private val onTVItemClickListener: OnTVItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(tv: TVUiModel) {
            itemView.tv_title.text = tv.name
            itemView.tv_desc.text = tv.overview
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + tv.posterPath)
                .into(itemView.tv_image)
            itemView.setOnClickListener {
                onTVItemClickListener?.setOnTVItemClickListener(tv.id)
            }
        }

    }

}