package com.vincent.recipe_mvvm_jetpack.network.model

import com.google.gson.annotations.SerializedName

/**
 * Model what the Recipe data looks like comes from the network
 * Dto stands for Data transfer object, an object that carries data between processes.
 */
data class RecipeDto(
    @SerializedName("pk")
    var pk: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("publisher")
    var publisher: String,

    @SerializedName("featured_image")
    var featuredImage: String,

    @SerializedName("rating")
    var rating: Int = 0,

    @SerializedName("source_url")
    var sourceUrl: String,

    @SerializedName("ingredients")
    var ingredients: List<String> = emptyList(),

    @SerializedName("date_added")
    var dateAdded: String,

    @SerializedName("date_updated")
    var dateUpdated: String,
)