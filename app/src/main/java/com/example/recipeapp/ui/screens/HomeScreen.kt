package com.example.recipeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.R
import com.example.recipeapp.model.RecipeData


@Composable
fun HomeScreen(
    recipeUiState: RecipeUiState,
    retryAction: () -> Unit,
    onRecipeCardClick:(Int)->Unit,
    onBookMarkClick:(RecipeData)->Unit,
    modifier: Modifier=Modifier
) {

    when (recipeUiState) {
        is RecipeUiState.Loading -> loadingScreen(modifier)
        is RecipeUiState.Success -> RecipeList(
            recipes = recipeUiState.recipeList,
            onClick = onRecipeCardClick,
            onBookMarkClick
        )
        is RecipeUiState.Error   -> ErrorScreen(retryAction,modifier)
    }
}



@Composable
fun RecipeCard(
    imageUrl: String,
    title: String,
    calories :String,
    carbs:String,
    fat:String,
    isBookMarked:Boolean,
    modifier: Modifier = Modifier,
    onClick:()->Unit,
    onBookMarkClick: () -> Unit

    ) {
    var isShowing by rememberSaveable{ mutableStateOf(false) }
    var isBookMarkShowing by remember{ mutableStateOf(isBookMarked)
    }
    Card(
        elevation = 5.dp,
        modifier = modifier
        .padding(4.dp)
        .padding(top = 10.dp)
        .border(2.dp, color = Color.Gray,
            shape = RoundedCornerShape(8.dp))
        .fillMaxWidth()
     ) {
        Row() {
            AsyncImage(
                model = ImageRequest.Builder(context= LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(20.dp)
                    .weight(.3f)
                    .align(alignment = Alignment.CenterVertically)
                    .border(1.dp, color = Color.LightGray,
                        shape = RoundedCornerShape(4.dp))

            )
            Column(modifier = Modifier
                .padding(4.dp)
                .padding(top = 30.dp)
                .weight(.6f)

            ) {
                Text( "title : $title", fontSize = 16.sp)
                Text("Calories : $calories", fontSize = 16.sp)
                if(isShowing) {

                    Text("Carbs : $carbs", fontSize = 16.sp)
                    Text("fat : $fat", fontSize = 16.sp)
                }
            }

            Column(
                modifier=Modifier
                    .padding(2.dp)
                    .weight(.1f)
            ) {
                IconButton(
                    onClick = {
                        isShowing = !isShowing
                        onClick()
                    },
                    modifier = Modifier
                        .padding(2.dp)

                ) {
                    Icon(
                        painter =  painterResource(R.drawable.ic_add),
                        contentDescription = null
                    )
                }

                IconButton(
                    onClick = {
                        isBookMarkShowing = !isBookMarkShowing
                        onBookMarkClick()
                    },
                    modifier = Modifier
                        .padding(2.dp)

                ) {
                    if(isBookMarkShowing){
                    Icon(
                        painter = painterResource(R.drawable.ic_bookmark),
                        contentDescription = null
                    )
                    }else if(!isBookMarkShowing){
                        Icon(
                            painter = painterResource(R.drawable.ic_bookmark_border),
                            contentDescription = null
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun RecipeList(
    recipes: List<RecipeData>,
    onClick: (Int) -> Unit,
    onBookMarkClick: (RecipeData) -> Unit
) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
       items(recipes){recipe->
               RecipeCard(
                   recipe.image,
                   recipe.title,
                   recipe.calories.toString(),
                   recipe.carbs,
                   recipe.fat,
                   recipe.isBookMarked,
                   modifier = Modifier,
               onClick = {
                   onClick(recipe.id)

               },
               onBookMarkClick={
                   onBookMarkClick(recipe)
               }
               )
           }

        }
}



@Composable
fun loadingScreen(modifier: Modifier=Modifier){
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
fun ErrorScreen(retryAction:()->Unit,modifier: Modifier=Modifier){
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
fun previewImageBox() {

   // RecipeCard("image", "title", modifier = Modifier)

}