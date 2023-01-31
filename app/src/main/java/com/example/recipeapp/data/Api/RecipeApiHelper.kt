package com.example.recipeapp.data.Api

import com.example.recipeapp.model.RecipeData
import com.example.recipeapp.model.RecipeDetailsData

interface RecipeApiHelper {
    suspend fun getRecipeDetails(): List<RecipeData>

    suspend fun getRecipeDetailsById(id:Int):RecipeDetailsData
}