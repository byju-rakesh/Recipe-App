package com.example.recipeapp.model

data class RecipeData(
    val calories: Int,
    val carbs: String,
    val fat: String,
    val id: Int,
    val image: String,
    val imageType: String,
    val protein: String,
    val title: String,
    var isBookMarked:Boolean=false
)