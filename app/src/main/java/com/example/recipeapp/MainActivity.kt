package com.example.recipeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.data.Db.RecipeDbData
import com.example.recipeapp.model.RecipeData
import com.example.recipeapp.ui.screens.HomeScreen

import com.example.recipeapp.ui.screens.RecipeViewModel
import com.example.recipeapp.ui.theme.RecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold (
                modifier=Modifier
                    .fillMaxSize(),
                topBar = { TopAppBar(title={ Text("Recipe App") }) }


            ) {


                val recipeViewModel = viewModel<RecipeViewModel>()
                HomeScreen(recipeUiState = recipeViewModel.recipeUiState,
                    retryAction = recipeViewModel::getRecipeDetails,
                   onRecipeCardClick = {id->

                       val iNext=Intent(this,RecipeDetailActivity::class.java)
                       iNext.putExtra("id",id)
                       openRecipeDetails(id,iNext)
                   },
                    onBookMarkClick={recipeData->
                        bookMarkClick(recipeData,recipeViewModel)
                    }
                    )



            }
        }
    }

    private fun  openRecipeDetails(id:Int,iNext:Intent){
        //todo launch recipe detail activity
        startActivity(iNext)

    }
    @SuppressLint("SuspiciousIndentation")
    private fun bookMarkClick(recipeData: RecipeData, recipeViewModel: RecipeViewModel){

        if(recipeData.isBookMarked)
        {
         recipeData.isBookMarked=false
            recipeViewModel.delete(
                id=recipeData.id
            )
            Toast.makeText(this@MainActivity,"deleted the recipe item",Toast.LENGTH_SHORT).show()
        }else{
            recipeData.isBookMarked=true
            recipeViewModel.insert(
                RecipeDbData(
                    id=recipeData.id,
                    title=recipeData.title
                )
            )
            Toast.makeText(this@MainActivity,"inserted the recipe item",Toast.LENGTH_SHORT).show()

        }

    }
}


