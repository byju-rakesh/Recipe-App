package com.example.recipeapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.RecipeRepository
import com.example.recipeapp.model.RecipeDetailsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface RecipeDetailUiState{
    data class Success(val recipeDetailList:RecipeDetailsData):RecipeDetailUiState
    object Error:RecipeDetailUiState
    object Loading:RecipeDetailUiState
}

@HiltViewModel
class RecipeDetailViewModel @Inject constructor (private val recipeRepository: RecipeRepository):
    ViewModel(){
    var recipeDetailUiState:RecipeDetailUiState by mutableStateOf(RecipeDetailUiState.Loading)
        private set



    fun getRecipeDetailsById(id:Int){
        viewModelScope.launch{
            recipeDetailUiState=RecipeDetailUiState.Loading
            recipeDetailUiState=try {
                RecipeDetailUiState.Success(recipeRepository.getRecipeDetailsById(id) )
            }catch (e: IOException){
                RecipeDetailUiState.Error
            }catch (e: HttpException){
                RecipeDetailUiState.Error
            }

        }
    }

}