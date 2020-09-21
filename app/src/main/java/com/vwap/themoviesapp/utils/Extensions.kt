package com.vwap.themoviesapp.utils

import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.vwap.themoviesapp.R

fun View.setOnSingleClickListener(l: View.OnClickListener) {
    setOnClickListener(OnSingleClickListener(l))
}

fun View.setOnSingleClickListener(l: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(l))
}

fun TextView.setCustomFont(t:Typeface){
    typeface = t
}

/**
 * This binding adapter lets us write app:imageUrl in XML for ImageViews
 * and everything works automagically! You gotta love data binding!
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.load(url) {
            crossfade(500)
            placeholder(R.drawable.poster_placeholder)
            error(R.drawable.poster_placeholder)
        }
    }
}