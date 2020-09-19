package com.vwap.themoviesapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vwap.themoviesapp.databinding.MovieItemBinding
import com.vwap.themoviesapp.model.MovieModel

class MoviesAdapter(
    private val layoutInflater: LayoutInflater,
    private val onMovieClickListener: OnMovieClickListener
) :
    ListAdapter<MovieModel, MovieItemViewHolder>(MovieDiffer) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder(
            MovieItemBinding.inflate(
                layoutInflater
            ), onMovieClickListener
        )

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) =
        holder.bindTo(getItem(position))


}

private object MovieDiffer : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
        areItemsTheSame(oldItem, newItem)

}