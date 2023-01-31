package com.example.recipeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.R
import com.example.recipeapp.model.RecipeData
import com.example.recipeapp.model.RecipeDetailsData

@Composable
fun RecipeDetailScreen(
    recipeDetailUiState: RecipeDetailUiState,
    id:Int,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
){


        when (recipeDetailUiState) {
            is RecipeDetailUiState.Loading -> loadingRecipeDetailScreen(modifier)
            is RecipeDetailUiState.Success -> RecipeDetailCardList(recipeDetailUiState.recipeDetailList)
            is RecipeDetailUiState.Error   -> ErrorRecipeDetailScreen(retryAction,modifier)
        }

}

@Composable
fun RecipeDetailCard(
     id:String,
     image:String,
     title:String,
     aggregateLikes:String,
     healthScore :String,
     spoonacularScore:String,
     pricePerServing: String,
    modifier: Modifier=Modifier
){
    Card(elevation = 5.dp, modifier = modifier
        .padding(16.dp)
        .padding(top = 10.dp)
        .border(2.dp, color = Color.Gray,
            shape = RoundedCornerShape(8.dp))
        .fillMaxWidth()
    ){
        Column() {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .weight(.3f)
                    .align(alignment = Alignment.CenterHorizontally)
                    .border(1.dp, color = Color.LightGray,
                        shape = RoundedCornerShape(4.dp))

            )

            Column(modifier = Modifier
                .padding(8.dp)
                .padding(top = 30.dp)
                .weight(.6f)
                .align(alignment = Alignment.CenterHorizontally)

            ){
                Text("Id : $id", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Text("title : $title", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Text("aggregateLikes : $aggregateLikes", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Text("healthScore : $healthScore", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Text("spoonacularScore : $spoonacularScore", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Text("pricePerServing : $pricePerServing", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)


            }

        }
    }
}

@Composable
fun RecipeDetailCardList(
    recipe: RecipeDetailsData
) { Column(
        modifier = Modifier.fillMaxWidth()

    ) {

            RecipeDetailCard(
                recipe.id.toString(),
                recipe.image,
                recipe.title,
                recipe.aggregateLikes.toString(),
                recipe.healthScore.toString(),
                recipe.spoonacularScore.toString(),
                recipe.pricePerServing.toString(),
                modifier = Modifier
                      )


    }
}






@Composable
fun loadingRecipeDetailScreen(modifier: Modifier=Modifier){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ){
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(R.drawable.loading_image),
            contentDescription = "Loading"
        )
    }
}

@Composable
fun ErrorRecipeDetailScreen(retryAction:()->Unit,modifier: Modifier=Modifier){
    Column (
        modifier=modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Failed to load")
        Button(onClick=retryAction){
            Text("Retry")
        }
    }
}

@Preview()
@Composable
fun previewRecipeDetailCard() {

 //RecipeDetailCard(1, "jsnb","detasnxj",12,2.3f,3.4f,354.3f, modifier = Modifier)

}