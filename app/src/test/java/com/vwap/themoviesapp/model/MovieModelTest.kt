package com.vwap.themoviesapp.model

import org.junit.Assert.assertEquals
import org.junit.Test

class MovieModelTest {

    @Test
    fun test_getPosterUrl() {
        val testMovieTitle = "Rogue"
        val testPosterPath = "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg"
        val movieModel = MovieModel(0, "", testPosterPath, "", testMovieTitle, 0.0, 0)
        assertEquals(
            "https://image.tmdb.org/t/p/w500/uOw5JD8IlD546feZ6oxbIjvN66P.jpg",
            movieModel.posterUrl
        )
    }

    @Test
    fun test_getImageTransitionName() {
        val testMovieTitle = "Rogue"
        val testPosterPath = "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg"
        val movieModel = MovieModel(0, "", testPosterPath, "", testMovieTitle, 0.0, 0)
        assertEquals(
            "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg_0",
            movieModel.imageTransitionName
        )
    }

    @Test
    fun test_getLabelTransitionName() {
        val testMovieTitle = "Rogue"
        val testPosterPath = "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg"
        val movieModel = MovieModel(0, "", testPosterPath, "", testMovieTitle, 0.0, 0)
        assertEquals("Rogue_0", movieModel.labelTransitionName)
    }
}