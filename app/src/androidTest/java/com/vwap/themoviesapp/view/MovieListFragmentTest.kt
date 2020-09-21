package com.vwap.themoviesapp.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.vwap.themoviesapp.R
import com.vwap.themoviesapp.model.MovieModel
import com.vwap.themoviesapp.repository.MoviesRepository
import io.mockk.every
import io.mockk.mockkConstructor
import org.junit.Test

class MovieListFragmentTest {
    @Test
    fun testMovieListFragment() {
        val testMovieTitle = "Rogue"
        val testPosterPath = "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg"
        val movieModel = MovieModel(0, "", testPosterPath, "", testMovieTitle, 0.0, 0)

        //mock the data received by list fragment
        val mockkkLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()
        mockkkLiveData.postValue(listOf(movieModel))
        mockkConstructor(MoviesRepository::class)
        every { anyConstructed<MoviesRepository>().getLiveData() } returns mockkkLiveData

        val scenario = launchFragmentInContainer<MovieListFragment>()
        onView(withId(R.id.movieList))
            .check(matches(hasChildCount(1)))

    }
}