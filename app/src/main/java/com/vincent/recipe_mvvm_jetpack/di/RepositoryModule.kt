package com.vincent.recipe_mvvm_jetpack.di

import com.vincent.recipe_mvvm_jetpack.network.RecipeService
import com.vincent.recipe_mvvm_jetpack.network.model.RecipeDtoMapper
import com.vincent.recipe_mvvm_jetpack.repository.RecipeRepository
import com.vincent.recipe_mvvm_jetpack.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Now we have the recipe repository that I can inject into the view model
    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeService, recipeDtoMapper)
    }
}