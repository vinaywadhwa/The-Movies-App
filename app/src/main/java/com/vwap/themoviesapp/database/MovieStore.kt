package com.vwap.themoviesapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieStore {
    @Query("SELECT * FROM MovieEntity")
//    fun all(): Flow<List<MovieEntity>> // TBD
    suspend fun all(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(entity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(entities: List<MovieEntity>)
}



