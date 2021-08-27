package com.vincent.recipe_mvvm_jetpack.network

import com.vincent.recipe_mvvm_jetpack.network.model.RecipeDto
import com.vincent.recipe_mvvm_jetpack.network.responses.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeService {

    // suspend = coroutine function
    @GET("search")
    suspend fun search(
        @Header("Authorization") token:String,
        @Query("page") page: Int,
        @Query("query") query:String
    ): RecipeSearchResponse


    @GET("get")
    suspend fun get(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): RecipeDto
}