package com.vwap.themoviesapp.utils

import android.graphics.Typeface
import android.view.View
import android.widget.TextView

fun View.setOnSingleClickListener(l: View.OnClickListener) {
    setOnClickListener(OnSingleClickListener(l))
}

fun View.setOnSingleClickListener(l: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(l))
}

fun TextView.setCustomFont(t:Typeface){
    typeface = t
}
