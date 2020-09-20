package com.vwap.themoviesapp.di

import android.app.Application
import com.vwap.themoviesapp.view_model.MoviesViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [AppModule::class])
interface ApplicationComponent {
    /**
     * Enables injection of fields in [MoviesViewModel]
     */
    fun inject(viewModel: MoviesViewModel)

    /**
     * We override the builder to inject the application context,
     * because one of the modules require it.
     */
    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        /**
         * Inject the application context, to be used by [AppModule.provideDatabase].
         */
        @BindsInstance
        fun setApplication(application: Application): Builder
        /**
         * Inject the application context, to be used by [AppModule.provideDatabase].
         */

        @BindsInstance
        fun setApiKey(@Named("apiKey")  apiKey: String): Builder
    }
}