package com.vwap.themoviesapp.view

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vwap.themoviesapp.R
import com.vwap.themoviesapp.databinding.MovieItemBinding
import com.vwap.themoviesapp.model.MovieModel
import com.vwap.themoviesapp.utils.setOnSingleClickListener


class MovieItemViewHolder(
    val binding: MovieItemBinding,
    private val onMovieClickListener: OnMovieClickListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnSingleClickListener {
            onMovieClickListener.onMovieClick(binding.item, binding.image)
        }
        animateView()
    }

    private fun animateView() {
        val animation: Animation =
            AnimationUtils.loadAnimation(binding.root.context, android.R.anim.fade_in)
        binding.root.startAnimation(animation)
    }

    fun bindTo(item: MovieModel?) {
        //set transition names to support transition animations
        ViewCompat.setTransitionName(binding.image, item?.imageTransitionName)

        binding.item = item
        binding.executePendingBindings()
    }

}

interface OnMovieClickListener {
    fun onMovieClick(movieModel: MovieModel?, image: View)
}


