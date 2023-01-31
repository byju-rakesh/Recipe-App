package com.example.recipeapp.data.Db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipeDbData: RecipeDbData)

    @Query("DELETE FROM BookMark WHERE id = :id")
   suspend fun delete(id: Int)

    @Query("SELECT* FROM BookMark")
   suspend fun getAllDbRecipeDetails():List<RecipeDbData>

}