package com.vwap.themoviesapp.api

import com.vwap.themoviesapp.model.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {
    @GET("3/discover/movie")
    fun getMovies(
        @Query("api_key") key: String,
        @Query("sort_by") sort: String = "popularity.desc"
    ): Call<MoviesList>
}