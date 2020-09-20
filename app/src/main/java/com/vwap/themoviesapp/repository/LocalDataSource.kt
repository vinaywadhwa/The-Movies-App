package com.vwap.themoviesapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vwap.themoviesapp.database.MovieDatabase
import com.vwap.themoviesapp.database.MovieEntity
import com.vwap.themoviesapp.model.MovieModel
import timber.log.Timber
import javax.inject.Inject

class LocalDataSource @Inject constructor(movieDatabase: MovieDatabase) {
    private val _results = MutableLiveData<List<MovieModel>>()
    val results: LiveData<List<MovieModel>> = _results
    private val db: MovieDatabase = movieDatabase

    suspend fun reload() {
        Timber.d("reloading data and posting")
        _results.postValue(db.movieStore().all().map { it.toModel() })
    }

    suspend fun insertOrReplace(data: List<MovieModel>) {
        Timber.d("updating data in local storage")
        db.movieStore().addAll(data.map { MovieEntity.fromModel(it) })
        reload()
    }

}