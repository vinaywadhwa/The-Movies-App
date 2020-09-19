package com.vwap.themoviesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DB_NAME: String = "nav_graph_movies.db¬"

@Database(entities = [MovieEntity::class], version = 2)
abstract class MovieDatabase : RoomDatabase() {


    abstract fun movieStore(): MovieStore

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        @Synchronized
        operator fun get(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME).build()
            }
            return INSTANCE!!
        }
    }
}