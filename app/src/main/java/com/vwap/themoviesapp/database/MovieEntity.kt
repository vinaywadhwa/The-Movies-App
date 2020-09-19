package com.vwap.themoviesapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vwap.themoviesapp.model.MovieModel
import timber.log.Timber

@Entity
class MovieEntity {

    @PrimaryKey
    var id: Int = 0

    var title: String = ""
    var poster_path: String = ""
    var adult: Boolean = false
    var backdrop_path: String = ""
    var vote_average: Double = 0.0
    var vote_count: Int = 0
    var overview: String = ""
    var release_date: String = ""

    /**
     * Converts a [MovieModel] to [MovieEntity] and returns it.
     */
    fun toModel(): MovieModel = MovieModel(
        id, overview, poster_path, release_date, title, vote_average, vote_count
    )

    companion object {
        /**
         * Factory function to create [MovieEntity] from a [MovieModel]
         */
        fun fromModel(model: MovieModel): MovieEntity = MovieEntity().apply {
            id = model.id
            overview = model.overview
            poster_path = model.poster_path
            release_date = model.release_date
            title = model.title
            vote_average = model.vote_average
            vote_count = model.vote_count
        }
    }
}