package com.example.recipeapp.data.Api

import com.example.recipeapp.model.RecipeData
import com.example.recipeapp.model.RecipeDetailsData
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {

    //https://api.spoonacular.com/recipes/findByNutrients

    //https://api.spoonacular.com/recipes/autocomplete
    //https://api.spoonacular.com/recipes/{id}/information

    @GET("recipes/findByNutrients?apiKey=69ddad241e4b4d49a95a8b4bb75a8d87&minCarbs=10")
    suspend fun getRecipeDetails():List<RecipeData>

    @GET("/recipes/{id}/information?apiKey=69ddad241e4b4d49a95a8b4bb75a8d87")
    suspend fun getRecipeDetailById(@Path(value="id") id:Int):RecipeDetailsData
}

