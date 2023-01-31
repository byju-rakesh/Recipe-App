package com.example.recipeapp.data.Db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeDbData::class], version = 1, exportSchema = true)
 abstract class RecipeDatabase :RoomDatabase() {
    abstract fun getRecipeDao():RecipeDao
}