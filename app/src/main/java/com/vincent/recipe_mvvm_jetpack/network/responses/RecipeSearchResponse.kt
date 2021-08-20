package com.vincent.recipe_mvvm_jetpack.network.responses

import com.google.gson.annotations.SerializedName
import com.vincent.recipe_mvvm_jetpack.network.model.RecipeDto

data class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>
)