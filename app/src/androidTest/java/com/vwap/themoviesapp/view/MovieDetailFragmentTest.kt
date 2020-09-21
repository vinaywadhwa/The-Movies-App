package com.vwap.themoviesapp.view

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.vwap.themoviesapp.R
import com.vwap.themoviesapp.model.MovieModel
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    @Test
    fun testMovieDetailFragment() {
        val testMovieTitle = "Rogue"
        val testPosterPath = "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg"
        val movieModel = MovieModel(0, "", testPosterPath, "", testMovieTitle, 0.0, 0)
        val bundle = Bundle().apply {
            putString("title", testMovieTitle)
            putParcelable("model", movieModel)
        }
        val scenario = launchFragmentInContainer<MovieDetailFragment>(bundle)
        onView(withId(R.id.label)).check(matches(withText(testMovieTitle)))
    }
}