package com.vwap.themoviesapp.database

import com.vwap.themoviesapp.model.MovieModel
import org.junit.Test

import org.junit.Assert.*

class MovieEntityTest {

    @Test
    fun test_toModel() {
        val testId = -234
        val testMovieTitle = "Rogue"
        val testMovieOverview = "Rogue is a very good movie."
        val testReleaseDate = "TEST_DATE"
        val testPosterPath = "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg"
        val testVoteAverage = 337.2131231028
        val testVoteCount = 2
        val movieModel = MovieModel(testId, testMovieOverview, testPosterPath, testReleaseDate, testMovieTitle, testVoteAverage, testVoteCount)
        val entity = MovieEntity.fromModel(movieModel)
        assertEquals(testId, entity.id)
        assertEquals(testMovieTitle, entity.title)
        assertEquals(testMovieOverview, entity.overview)
        assertEquals(testReleaseDate, entity.release_date)
        assertEquals(testPosterPath, entity.poster_path)
        assertEquals(testVoteAverage, entity.vote_average, 0.00001)
        assertEquals(testVoteCount, entity.vote_count)
    }
}