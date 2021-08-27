package com.vincent.recipe_mvvm_jetpack.repository

import com.vincent.recipe_mvvm_jetpack.domain.model.Recipe

/**
 * Function return list of Recipe, not list of RecipeDto
 * Because UI only cares about Recipe objects
 * UI should never see the Dto(Network Entities)
 */
interface RecipeRepository {
    suspend fun search(token: String, page: Int, query: String): List<Recipe>
    suspend fun get(token: String, id: Int): Recipe
}