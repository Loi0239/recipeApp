package com.example.recipeapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.ui.favouriteRecipe.FavouriteDestination
import com.example.recipeapp.ui.home.HomeDestination
import com.example.recipeapp.ui.navigation.RecipeNavHost
import com.example.recipeapp.ui.schedule.ScheduleDestination
import com.example.recipeapp.ui.recipe_person.ShowRecipeDestination
import com.example.recipeapp.ui.recipe_person.add_recipe.AddRecipeDestination
import com.example.recipeapp.ui.shoppingList.ShoppingListDestination

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecipeApp(
    navController: NavHostController = rememberNavController(),
    recipeAppViewModel: RecipeAppViewModel = viewModel(factory = RecipeAppViewModel.Factory)
){
    val footerState = recipeAppViewModel.footerState.value
    Box(modifier = Modifier.fillMaxSize()) {
        RecipeNavHost(
            navController = navController,
            recipeAppViewModel = recipeAppViewModel
        )
        if(footerState){
            Footer(
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun Footer(
    navController: NavController,
    modifier: Modifier = Modifier
){
    val viewModel: RecipeAppViewModel = viewModel()
    Box(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val footerItems = viewModel.footerItems.observeAsState(initial = emptyList()).value
            footerItems.forEach { footerItem ->
                Icon(
                    footerItem.nameIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            viewModel.onItemClicked(footerItem.id)
                            when (footerItem.id) {
                                1 -> {
                                    navController.navigate(HomeDestination.route)
                                }

                                2 -> {
                                    navController.navigate(FavouriteDestination.route)
                                }

                                3 -> {
                                    navController.navigate(AddRecipeDestination.route)
                                }

                                4 -> {
                                    navController.navigate(ShoppingListDestination.route)
                                }

                                5 -> {
                                    navController.navigate(ShowRecipeDestination.route)
                                }
                            }
                        }
                        .let {
                            if (footerItem.id == 3) {
                                it
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(color = colorResource(id = R.color.primaryColor))
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                            } else if (footerItem.checked) {
                                it
                                    .background(color = Color(0xffECECEC))
                                    .padding(8.dp)
                            } else {
                                it
                                    .background(color = Color.Unspecified)
                                    .padding(8.dp)
                            }
                        },
                    tint = if(footerItem.id == 3){
                        Color.White
                    }else if (footerItem.checked) {
                        colorResource(id = R.color.primaryColor)
                    } else {
                        Color.Unspecified
                    }
                )
            }
        }
    }
}