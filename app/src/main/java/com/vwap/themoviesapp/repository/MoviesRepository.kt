package com.vwap.themoviesapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.vwap.themoviesapp.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

    private val cacheUpdaterLambda = Observer<List<MovieModel>> {
        /**
         * using global scope because we must save the data despite of the view state
         * Although, do remember to unregister the observer after the view is destroyed. See [deinit]
         */
        GlobalScope.launch(Dispatchers.IO) {
            if (it.isNotEmpty()) {
                Timber.d("Updating cache with result received from remote data source.")
                localDataSource.insertOrReplace(it)
                localDataSource.reload()
            } else {
                Timber.d("Not updating cache, empty result received from remote data source.")
            }
        }
    }

    init {
        Timber.d("triggering initial fetch from remote DS")
//        remoteDataSource.fetch(context.getString(R.string.api_key)) // TODO inject this!
        remoteDataSource.fetch()
        remoteDataSource.results.observeForever(cacheUpdaterLambda)
    }

    fun getLiveData(): LiveData<List<MovieModel>> = localDataSource.results

    fun refresh() {
        Timber.d("triggering remote fetch")
        remoteDataSource.fetch()
    }


    fun deinit() {
        Timber.d("removing remote repo observer")
        remoteDataSource.results.removeObserver(cacheUpdaterLambda)
    }

    suspend fun init() {
        Timber.d("loading from local AND triggering remote refresh")
        localDataSource.reload()
        refresh()
    }
}
