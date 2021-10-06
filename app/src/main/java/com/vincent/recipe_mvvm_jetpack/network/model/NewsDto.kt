package com.vincent.recipe_mvvm_jetpack.network.model

import com.google.gson.annotations.SerializedName

/**
 * Model what the Recipe data looks like comes from the network
 * Dto stands for Data transfer object, an object that carries data between processes.
 */
data class NewsDto(
    @SerializedName("link")
    var link: String,

    @SerializedName("createAt")
    var createAt: String,

    @SerializedName("month")
    var month: String,
)