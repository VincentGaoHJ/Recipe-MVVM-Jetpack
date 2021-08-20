package com.vincent.recipe_mvvm_jetpack.di

import android.content.Context
import com.vincent.recipe_mvvm_jetpack.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// ApplicationComponent injector for application
// This top level one is scoped to the application
// Dependencies in the ApplicationComponent will live as long as the application is alive
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Scopes that defines the lifetime of that dependency
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }


    @Singleton
    @Provides
    fun provideRandomString(): String {
        return "Hey look a random String!!!"
    }
}