package com.example.recipeapp.model

data class RecipeDetailsData(
    val id:Int,
    val image:String,
    val title:String,
    val aggregateLikes:Int,
    val healthScore :Float,
    val spoonacularScore:Float,
    val pricePerServing: Float
)
