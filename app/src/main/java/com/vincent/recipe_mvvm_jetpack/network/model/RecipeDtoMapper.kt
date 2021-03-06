package com.vincent.recipe_mvvm_jetpack.network.model

import com.vincent.recipe_mvvm_jetpack.domain.model.Recipe
import com.vincent.recipe_mvvm_jetpack.domain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {
    /**
     * Take a Network Entity(RecipeDto) as input and return a Domain(Recipe)
     */
    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            ingredients = model.ingredients,
            dateAdded = model.dateAdded,
            dateUpdated = model.dateUpdated,
        )
    }

    /**
     * Take Domain(Recipe) as input and return a Network Entity(RecipeDto)
     * It's not gonna be used if you don't publish something to the network
     */
    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            ingredients = domainModel.ingredients,
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated,
        )
    }

    fun toDomainList(inital: List<RecipeDto>): List<Recipe> {
        return inital.map { mapToDomainModel(it) }
    }

    fun fromDomainList(inital: List<Recipe>): List<RecipeDto> {
        return inital.map { mapFromDomainModel(it) }
    }
}