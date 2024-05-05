package com.example.recipeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.login.LoginDestination
import com.example.recipeapp.ui.login.LoginScreen

@Composable
fun RecipeNavHost(
    navController: NavHostController,
    recipeAppViewModel: RecipeAppViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination.route,
        modifier = modifier
    ){
        composable(route = LoginDestination.route){
            LoginScreen(
//                navigateToHomeScreen = { navController.navigate(HomeDestination.route) },
//                navigateToSignUp = { navController.navigate(SignUpDestination.route) },
                recipeAppViewModel = recipeAppViewModel,
            )
        }
    }
}