package com.example.recipeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipesApplication :Application(){
    override fun onCreate() {
        super.onCreate()

    }
}