package com.vwap.themoviesapp

import android.app.Application
import com.vwap.themoviesapp.di.ApplicationComponent
import com.vwap.themoviesapp.di.DaggerApplicationComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class MyApplication : Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        appComponent = DaggerApplicationComponent
            .builder()
            .setApplication(this)
            .setApiKey(getString(R.string.api_key))
            .build()

        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}