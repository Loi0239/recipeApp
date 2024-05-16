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
import com.example.recipeapp.ui.product.category_product.CategoryProductScreen
import com.example.recipeapp.ui.product.category_product.CategoryProductScreenDestination
import com.example.recipeapp.ui.product.find_name_product.FindNameProScreen
import com.example.recipeapp.ui.product.find_name_product.FindNameProScreenDestination
import com.example.recipeapp.ui.recipe.IngredientDestination
import com.example.recipeapp.ui.recipe.LayoutIngredient
import com.example.recipeapp.ui.recipe_person.RecipePerson
import com.example.recipeapp.ui.recipe_person.ShowRecipeDestination
import com.example.recipeapp.ui.recipe_person.add_recipe.AddRecipe
import com.example.recipeapp.ui.recipe_person.add_recipe.AddRecipeDestination
import com.example.recipeapp.ui.recipe_person.update_recipe.UpdateRecipe
import com.example.recipeapp.ui.recipe_person.update_recipe.UpdateRecipeDestination
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
        startDestination = HomeDestination.route,
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
                recipeAppViewModel = recipeAppViewModel,
                navigateToAllProductScreen = { navController.navigate(AllProductScreenDestination.route) },
                navigateToCategoryProductScreen= {
                    navController.navigate("${CategoryProductScreenDestination.route}/${it}")
                },
                navigateToRecipeDetailScreen= {
                    navController.navigate("${IngredientDestination.route}/${it}")
                },
                navigateToFindNameProScreen= {
                    navController.navigate("${FindNameProScreenDestination.route}/${it}")
                },
                navigateToAddRecipe = { navController.navigate(AddRecipeDestination.route) },
                navigateToShowRecipe = { navController.navigate(ShowRecipeDestination.route) },
            )
        }

       /* composable(route = HomeDestination.route){
            HomeScreen(
                recipeAppViewModel = recipeAppViewModel,
                navToHome = { navController.navigate(HomeDestination.route) },
                navigateToAllProductScreen = { navController.navigate(AllProductScreenDestination.route) },
                navigateToCategoryProductScreen= {
                    navController.navigate("${CategoryProductScreenDestination.route}/${it}")
                },
                navigateToRecipeDetailScreen= {
                    navController.navigate("${IngredientDestination.route}/${it}")
                },
                navigateToFindNameProScreen= {
                    navController.navigate("${FindNameProScreenDestination.route}/${it}")
                },
                navigateToAddRecipe = { navController.navigate(AddRecipeDestination.route) },
                navigateToShowRecipe = { navController.navigate(ShowRecipeDestination.route) },
            )
        }*/
        composable(route = AllProductScreenDestination.route){
            LayoutAllRecipe(
                navigateBack = {navController.popBackStack()},
                navigateToRecipeDetailScreen= {
                    navController.navigate("${IngredientDestination.route}/${it}")
                },
                recipeAppViewModel = recipeAppViewModel
            )
        }
        composable(
            route = CategoryProductScreenDestination.routeWithId1,
            arguments = listOf(navArgument(CategoryProductScreenDestination.categoryId) {
                type = NavType.IntType
            })
        ){
            CategoryProductScreen(
                navigateBack = {navController.popBackStack()},
                navToCategoryProduct = { navController.navigate(CategoryProductScreenDestination.route) },
                navigateToRecipeDetailScreen= {
                    navController.navigate("${IngredientDestination.route}/${it}")
                }
            )
        }
        composable(
            route = FindNameProScreenDestination.routeWithProductName,
            arguments = listOf(navArgument(FindNameProScreenDestination.keyproductName) {
                type = NavType.StringType
            })
        ){
            FindNameProScreen(
                navigateBack = {navController.popBackStack()},
                navToFindNamePro = { navController.navigate(FindNameProScreenDestination.route) },
                navigateToRecipeDetailScreen= {
                    navController.navigate("${IngredientDestination.route}/${it}")
                },
            )
        }
        composable(route = ShowRecipeDestination.route){
            RecipePerson(
                navigateBack = {navController.popBackStack()},
                navToShowRecipe = { navController.navigate(ShowRecipeDestination.route) },
                navigateToUpdateRecipe = {
                    navController.navigate("${UpdateRecipeDestination.route}/${it}")
                },
            )
        }
        composable(route = AddRecipeDestination.route){
            AddRecipe(
                navigateBack = {navController.popBackStack()},
                navToAddRecipe = { navController.navigate(AddRecipeDestination.route) },
                navigateToUpdateRecipe = {
                    navController.navigate("${UpdateRecipeDestination.route}/${it}")
                },
            )
        }
        composable(
            route = UpdateRecipeDestination.routeWithID,
            arguments = listOf(navArgument(UpdateRecipeDestination.idRecipe) {
                type = NavType.IntType
            })
        ){
            UpdateRecipe(
                navigateBack = {navController.popBackStack()},
                navToUpdateRecipe = { navController.navigate(UpdateRecipeDestination.route) },
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
