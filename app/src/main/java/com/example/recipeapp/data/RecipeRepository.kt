package com.example.recipeapp.data

import com.example.recipeapp.data.Api.RecipeApiHelper
import com.example.recipeapp.data.Db.RecipeDao
import com.example.recipeapp.data.Db.RecipeDbData
import com.example.recipeapp.model.RecipeData
import com.example.recipeapp.model.RecipeDetailsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RecipeRepository{
    suspend fun getRecipeDetails():List<RecipeData>
    suspend fun getRecipeDetailsById(id:Int):RecipeDetailsData
    suspend fun  insert(recipeDbData: RecipeDbData)
    suspend fun  delete(id: Int)
    suspend fun  getAllDbRecipeDetails():List<RecipeDbData>

}

class ApiRecipeRepository @Inject constructor(private val recipeApiHelper: RecipeApiHelper,private val recipeDao: RecipeDao)
   :RecipeRepository{
    override suspend fun getRecipeDetails(): List<RecipeData> =recipeApiHelper.getRecipeDetails()

    override suspend fun getRecipeDetailsById(id: Int):RecipeDetailsData =recipeApiHelper.getRecipeDetailsById(id)

    override suspend fun insert(recipeDbData: RecipeDbData)= recipeDao.insert(recipeDbData)
    override  suspend fun getAllDbRecipeDetails():List<RecipeDbData> = recipeDao.getAllDbRecipeDetails()

    override suspend fun delete(id: Int) =recipeDao.delete(id)

}