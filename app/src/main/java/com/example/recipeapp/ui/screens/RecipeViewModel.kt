package com.example.recipeapp.ui.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.Db.RecipeDbData
import com.example.recipeapp.data.RecipeRepository
import com.example.recipeapp.model.RecipeData
import com.example.recipeapp.model.RecipeDetailsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface RecipeUiState{
    data class Success(val recipeList: List<RecipeData>):RecipeUiState
    object Error:RecipeUiState
    object Loading:RecipeUiState
}
@HiltViewModel
class RecipeViewModel @Inject constructor (private val recipeRepository: RecipeRepository):ViewModel(){
    var recipeUiState:RecipeUiState by mutableStateOf(RecipeUiState.Loading)
     var response:List<RecipeDbData> by mutableStateOf(listOf())
    private set

    init {
        getRecipeDetails()
    }


    fun getRecipeDetails(){
        viewModelScope.launch {
            recipeUiState=RecipeUiState.Loading
            recipeUiState=try {
           val bookMarkList =  recipeRepository.getAllDbRecipeDetails()
            val apiRecipeList =recipeRepository.getRecipeDetails()
                bookMarkList.forEach {
                   apiRecipeList.forEach { recipeData ->
                       if(recipeData.id==it.id){
                           recipeData.isBookMarked=true
                       }
                   }
                }

                RecipeUiState.Success( apiRecipeList)
            }catch (e:Exception){
                RecipeUiState.Error
            }

        }
    }

    fun insert(recipeDbData: RecipeDbData) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.insert(recipeDbData)
        }
    }
    fun getAllDbRecipeDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            response = recipeRepository.getAllDbRecipeDetails()

        }
    }
    fun delete(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.delete(id)
        }
    }
}

