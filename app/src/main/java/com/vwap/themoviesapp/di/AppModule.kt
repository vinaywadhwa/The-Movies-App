package com.vwap.themoviesapp.di

import android.app.Application
import com.vwap.themoviesapp.database.MovieDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideDatabase(application: Application): MovieDatabase = MovieDatabase[application]
}