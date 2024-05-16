package com.example.recipeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.favouriteRecipe.FavouriteDestination
import com.example.recipeapp.ui.favouriteRecipe.RecipeFavoriteScreen
import com.example.recipeapp.ui.home.HomeDestination
import com.example.recipeapp.ui.home.HomeScreen
import com.example.recipeapp.ui.login.LoginDestination
import com.example.recipeapp.ui.login.LoginScreen
import com.example.recipeapp.ui.product.AllProductScreenDestination
import com.example.recipeapp.ui.product.LayoutAllRecipe
import com.example.recipeapp.ui.recipe.IngredientDestination
import com.example.recipeapp.ui.recipe.LayoutIngredient
import com.example.recipeapp.ui.shoppingList.ShoppingListDestination
import com.example.recipeapp.ui.shoppingList.ShoppingListScreen

@Composable
fun RecipeNavHost(
    navController: NavHostController,
    recipeAppViewModel: RecipeAppViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ShoppingListDestination.route,
        modifier = modifier
    ){
        composable(route = LoginDestination.route){
            LoginScreen(
//                navigateToHomeScreen = { navController.navigate(HomeDestination.route) },
//                navigateToSignUp = { navController.navigate(SignUpDestination.route) },
                recipeAppViewModel = recipeAppViewModel,
            )
        }

        composable(route = HomeDestination.route){
            HomeScreen(
                navToHome = {},
                recipeAppViewModel = recipeAppViewModel
            )
        }

        composable(route = FavouriteDestination.route){
            RecipeFavoriteScreen(
                navigateToRecipeDetailScreen={
                    navController.navigate("${IngredientDestination.route}/${it}")
                },
                recipeAppViewModel = recipeAppViewModel
            )
        }

        composable(route = AllProductScreenDestination.route){
            LayoutAllRecipe(
                navToAllProductScreen = { navController.navigate(AllProductScreenDestination.route) },
                navigateToRecipeDetailScreen= {
                    navController.navigate("${IngredientDestination.route}/${it}")
                },
                recipeAppViewModel = recipeAppViewModel,
            )
        }

        composable(
            route =  IngredientDestination.routeWithId,
            arguments = listOf(navArgument(IngredientDestination.productId) {
                type = NavType.IntType
            })
        ){
            LayoutIngredient(
                recipeAppViewModel = recipeAppViewModel,
                navToBack = {navController.popBackStack()}
            )
        }

        composable(
            route = ShoppingListDestination.route
        ){
            ShoppingListScreen(
                recipeAppViewModel = recipeAppViewModel
            )
        }
    }
}
