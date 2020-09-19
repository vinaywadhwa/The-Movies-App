package com.vwap.themoviesapp.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vwap.themoviesapp.model.MovieModel
import com.vwap.themoviesapp.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(myApp: Application) :
    AndroidViewModel(myApp) {
    private val repo = MoviesRepository(myApp)
    val moviesList: LiveData<List<MovieModel>> = repo.getLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.init()
        }
    }

    fun triggerRefresh() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.refresh()
        }
    }

    override fun onCleared() {
        super.onCleared()
        //so the repo can deregister live data observer (used for caching web response to db)
        repo.deinit()
    }
}
