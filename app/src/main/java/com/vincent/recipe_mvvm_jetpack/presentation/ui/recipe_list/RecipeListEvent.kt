package com.vincent.recipe_mvvm_jetpack.presentation.ui.recipe_list

sealed class RecipeListEvent {

    object NewSearchEvent: RecipeListEvent()

    object NextPageEvent: RecipeListEvent()

    object RestoreStateEvent: RecipeListEvent()
}