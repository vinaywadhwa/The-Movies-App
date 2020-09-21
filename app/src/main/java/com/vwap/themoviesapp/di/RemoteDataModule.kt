package com.vwap.themoviesapp.di

import android.app.Application
import com.vwap.themoviesapp.api.ApiEndpoints
import com.vwap.themoviesapp.api.ServiceBuilder
import dagger.Module
import dagger.Provides

@Module
class RemoteDataModule {

    @Provides
    fun provideApiEndpoints(application: Application): ApiEndpoints = ServiceBuilder.buildService(
        ApiEndpoints::class.java
    )
}