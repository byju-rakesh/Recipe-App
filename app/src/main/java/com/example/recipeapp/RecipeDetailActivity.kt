package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.ui.screens.HomeScreen
import com.example.recipeapp.ui.screens.RecipeDetailScreen
import com.example.recipeapp.ui.screens.RecipeDetailViewModel
import com.example.recipeapp.ui.screens.RecipeViewModel
import com.example.recipeapp.ui.theme.RecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold (
                modifier=Modifier
                    .fillMaxSize(),
                topBar = { TopAppBar(title={ Text("Recipe Detail") }) }


            ) {
                val context = LocalContext.current
                val intent = (context as RecipeDetailActivity).intent
                val id=intent.getIntExtra("id",-1)
                val recipeDetailViewModel = viewModel<RecipeDetailViewModel>()
               LaunchedEffect(Unit){ recipeDetailViewModel.getRecipeDetailsById(id)}
                RecipeDetailScreen(recipeDetailUiState = recipeDetailViewModel.recipeDetailUiState,
                    id,
                    retryAction = {recipeDetailViewModel.getRecipeDetailsById(id)}

                )

            }
        }
    }
}

