package com.vincent.recipe_mvvm_jetpack.repository

import com.vincent.recipe_mvvm_jetpack.domain.model.Recipe

interface RecipeRepository {

    suspend fun search(token: String, page: Int, query: String): List<Recipe>
    suspend fun get(token: String, id: Int): Recipe
}