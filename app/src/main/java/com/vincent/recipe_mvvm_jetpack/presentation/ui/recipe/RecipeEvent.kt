package com.vincent.recipe_mvvm_jetpack.presentation.ui.recipe

sealed class RecipeEvent {
    data class GetRecipeEvent(
        val id: Int
    ) : RecipeEvent()
}