package com.vincent.recipe_mvvm_jetpack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Core Business Model
 * See Recipe example: https://food2fork.ca/
 * @Parcelize requires all serialized properties to be declared in the primary constructor.
 */
@Parcelize
data class Recipe(
    val id: Int,
    val title: String,
    val publisher: String,
    val featuredImage: String,
    val rating: Int,
    val sourceUrl: String,
    val ingredients: List<String> = listOf(),
    val dateAdded: String,
    val dateUpdated: String,
) : Parcelable