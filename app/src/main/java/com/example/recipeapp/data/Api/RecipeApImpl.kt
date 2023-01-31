package com.example.recipeapp.data.Api

import com.example.recipeapp.model.RecipeData
import com.example.recipeapp.model.RecipeDetailsData
import javax.inject.Inject

class RecipeApImpl @Inject constructor(private val recipeApiService: RecipeApiService):RecipeApiHelper {
    override suspend fun getRecipeDetails(): List<RecipeData> = recipeApiService.getRecipeDetails()

    override suspend fun getRecipeDetailsById(id: Int):RecipeDetailsData =recipeApiService.getRecipeDetailById(id)

}