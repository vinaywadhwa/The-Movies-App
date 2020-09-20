package com.vwap.themoviesapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vwap.themoviesapp.api.ApiEndpoints
import com.vwap.themoviesapp.api.ServiceBuilder
import com.vwap.themoviesapp.model.MovieModel
import com.vwap.themoviesapp.model.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class RemoteDataSource @Inject constructor(@Named("apiKey") private val apiKey: String){
    private val _results = MutableLiveData<List<MovieModel>>(listOf())
    val results: LiveData<List<MovieModel>> = _results

    fun fetch() {
        Timber.d("fetch called!")
        val request = ServiceBuilder.buildService(ApiEndpoints::class.java)
        val call = request.getMovies(apiKey)

        //fetch fresh data from
        call.enqueue(object : Callback<MoviesList> {
            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                Timber.d("FAILED to fetch New data - posting to live data")
                _results.postValue(listOf())
            }

            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                Timber.d("New data fetched - posting to live data")
                val movies = response.body()?.results as MutableList<MovieModel>
                _results.postValue(movies)
            }
        })
    }

}