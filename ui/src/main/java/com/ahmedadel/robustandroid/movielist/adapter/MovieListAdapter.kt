package com.ahmedadel.robustandroid.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadel.robustandroid.BuildConfig
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.presentation.mvp.movielist.uimodel.MovieUiModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_list_item.view.*

/**
 * Created at Tito on 3/17/19
 */

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val movies: MutableList<MovieUiModel> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.activity_movie_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    fun submitList(newMovies: List<MovieUiModel>) {
        movies.addAll(newMovies)
        val uniqueMovieList = movies.distinctBy { movie ->
            movie.id
        }
        movies.clear()
        movies.addAll(uniqueMovieList)
        notifyDataSetChanged()
    }

    fun clear() {
        movies.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: MovieUiModel) {
            itemView.movie_title.text = movie.title
            itemView.movie_desc.text = movie.overview
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + movie.posterPath)
                .into(itemView.movie_image)
        }

    }

}