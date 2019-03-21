package com.ahmedadel.robustandroid.moviedetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedadel.robustandroid.BuildConfig
import com.ahmedadel.robustandroid.R
import com.ahmedadel.robustandroid.presentation.mvi.movie.uimodel.MovieUiModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_list_item.view.*

/**
 * Created at Tito on 3/21/19
 */

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>() {

    private val movies: MutableList<MovieUiModel> = ArrayList()

    var onSimilarMovieClickListener: OnSimilarMovieClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.movie_list_item, viewGroup, false),
            onSimilarMovieClickListener
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    fun submitList(newMovies: List<MovieUiModel>?) {
        newMovies?.let {
            movies.addAll(it)
            val uniqueMovieList = movies.distinctBy { movie ->
                movie.id
            }
            movies.clear()
            movies.addAll(uniqueMovieList)
            notifyDataSetChanged()
        }
    }

    @Suppress("unused")
    fun clear() {
        movies.clear()
        notifyDataSetChanged()
    }

    interface OnSimilarMovieClickListener {
        fun setOnSimilarMovieClickListener(movieId: Int)
    }

    class ViewHolder(itemView: View, private val onSimilarMovieClickListener: OnSimilarMovieClickListener?) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(movie: MovieUiModel) {
            itemView.movie_title.text = movie.title
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + movie.posterPath)
                .into(itemView.movie_image)
            itemView.setOnClickListener {
                onSimilarMovieClickListener?.setOnSimilarMovieClickListener(movie.id)
            }
        }

    }

}