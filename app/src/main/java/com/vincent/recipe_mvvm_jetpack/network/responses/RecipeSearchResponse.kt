package com.vincent.recipe_mvvm_jetpack.network.responses

import com.google.gson.annotations.SerializedName
import com.vincent.recipe_mvvm_jetpack.network.model.RecipeDto

/**
 * Model the response from the network when you do a search
 */
data class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>
)