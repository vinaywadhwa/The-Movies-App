package com.vwap.themoviesapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MoviesList(
    val results: List<MovieModel>
)

@Parcelize
data class MovieModel(
    val id: Int, val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500/${poster_path}"
    val imageTransitionName: String
        get() = "${poster_path}_${id}"
    val labelTransitionName: String
        get() = "${title}_${id}"

}
