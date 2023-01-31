package com.example.recipeapp.di

import android.app.Application
import androidx.room.Room
import com.example.recipeapp.data.Api.RecipeApImpl
import com.example.recipeapp.data.Api.RecipeApiHelper
import com.example.recipeapp.data.Api.RecipeApiService
import com.example.recipeapp.data.ApiRecipeRepository
import com.example.recipeapp.data.Db.RecipeDao
import com.example.recipeapp.data.Db.RecipeDatabase
import com.example.recipeapp.data.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApllicationModule {
    private val BASE_URL =
        "https://api.spoonacular.com/"
    //recipes?apiKey=69ddad241e4b4d49a95a8b4bb75a8d87/


    @Provides
    @Singleton
    fun provideRetrofit():Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit)=retrofit.create(RecipeApiService::class.java)

    @Provides
    @Singleton
    fun privideApiHelper(recipeApiHelper: RecipeApImpl):RecipeApiHelper=recipeApiHelper

    @Provides
    fun providesApiRecipeRepository(apiRecipeRepository: ApiRecipeRepository):RecipeRepository{
        return apiRecipeRepository
    }


    @Provides
    @Singleton
    fun providesDataBase(application: Application):RecipeDatabase=
        Room.databaseBuilder(application,RecipeDatabase::class.java,"RecipeDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesRecipeDao(db:RecipeDatabase):RecipeDao=db.getRecipeDao()
}