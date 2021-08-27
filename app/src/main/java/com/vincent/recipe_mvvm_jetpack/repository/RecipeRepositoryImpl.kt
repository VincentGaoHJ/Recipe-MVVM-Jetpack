package com.vincent.recipe_mvvm_jetpack.repository

import com.vincent.recipe_mvvm_jetpack.domain.model.Recipe
import com.vincent.recipe_mvvm_jetpack.network.RecipeService
import com.vincent.recipe_mvvm_jetpack.network.model.RecipeDtoMapper

/**
 * Take parameters as function input
 * Call retrofit to get results(Dto) from network
 * Call function(toDomainList/mapToDomainModel) to transfer Dto to Domain
 * Return Domain object as function output
 */
class RecipeRepositoryImpl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val result = recipeService.search(token, page, query).recipes
        return mapper.toDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        val result = recipeService.get(token, id)
        return mapper.mapToDomainModel(result)
    }
}