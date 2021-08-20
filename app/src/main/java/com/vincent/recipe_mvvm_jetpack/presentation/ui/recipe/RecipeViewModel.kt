package com.vincent.recipe_mvvm_jetpack.presentation.ui.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vincent.recipe_mvvm_jetpack.domain.model.Recipe
import com.vincent.recipe_mvvm_jetpack.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

import com.vincent.recipe_mvvm_jetpack.presentation.ui.recipe.RecipeEvent.GetRecipeEvent
import com.vincent.recipe_mvvm_jetpack.util.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val STATE_KEY_RECIPE = "recipe.state.recipe.key"

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    @Named("auth_token") private val token: String,
    private val state: SavedStateHandle
) : ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    init {
        // Restore if process dies
        state.get<Int>(STATE_KEY_RECIPE)?.let { recipeId ->
            onTriggerEvent(GetRecipeEvent(recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeEvent) {

        viewModelScope.launch {
            try {
                when (event) {
                    is GetRecipeEvent -> {
                        Log.d(TAG, "onTriggerEvent: Recipe ID: ${event.id}")
                        if (recipe.value == null) {
                            getRecipe(event.id)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "onTriggerEvent: Exception$e, ${e.cause}")
            }
        }
    }

    private suspend fun getRecipe(id: Int) {
        loading.value = true

        // Simulate a delay to show loading
        delay(1000)

        val recipe = recipeRepository.get(
            token = token,
            id = id
        )

        this.recipe.value = recipe
        state.set(STATE_KEY_RECIPE, recipe.id)

        loading.value = false
    }
}