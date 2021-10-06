package com.vincent.recipe_mvvm_jetpack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Core Business Model
 * See News example:
 * @Parcelize requires all serialized properties to be declared in the primary constructor.
 */
@Parcelize
data class News(
    val link: String,
    val createAt: String,
    val month: String
) : Parcelable