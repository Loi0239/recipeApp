package com.example.recipeapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.recipeapp.ui.product.all_product.AllProductScreenDestination
import com.example.recipeapp.ui.product.all_product.LayoutAllRecipe
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
import com.example.recipeapp.ui.recipe_person.recipe_detail.RecipeDetailPerson
import com.example.recipeapp.ui.recipe_person.recipe_detail.RecipeDetailPersonDestination
import com.example.recipeapp.ui.recipe_person.update_recipe.UpdateRecipe
import com.example.recipeapp.ui.recipe_person.update_recipe.UpdateRecipeDestination
import com.example.recipeapp.ui.schedule.ScheduleDestination
import com.example.recipeapp.ui.schedule.ScheduleScreen
import com.example.recipeapp.ui.schedule.findingForSchedule.FindForScheduleDestination
import com.example.recipeapp.ui.schedule.findingForSchedule.FindForScheduleScreen
import com.example.recipeapp.ui.shoppingList.ShoppingListDestination
import com.example.recipeapp.ui.shoppingList.ShoppingListScreen

@RequiresApi(Build.VERSION_CODES.O)
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
        // Trang chủ
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
            )
        }
        // Tất cả công thức
        composable(route = AllProductScreenDestination.route){
            LayoutAllRecipe(
                navigateToRecipeDetailScreen= {
                    navController.navigate("${IngredientDestination.route}/${it}")
                },
                recipeAppViewModel = recipeAppViewModel
            )
        }
        // Danh mục công thức
        composable(
            route = CategoryProductScreenDestination.routeWithId,
            arguments = listOf(navArgument(CategoryProductScreenDestination.categoryId) {
                type = NavType.IntType
            })
        ){
            CategoryProductScreen(
                recipeAppViewModel = recipeAppViewModel,
                navigateBack = {navController.popBackStack()},
                navigateToRecipeDetailScreen= {
                    navController.navigate("${IngredientDestination.route}/${it}")
                }
            )
        }
        // Tìm kiếm công thức
        composable(
            route = FindNameProScreenDestination.routeWithProductName,
            arguments = listOf(navArgument(FindNameProScreenDestination.keyproductName) {
                type = NavType.StringType
            })
        ){
            FindNameProScreen(
                recipeAppViewModel = recipeAppViewModel,
                navigateBack = {navController.popBackStack()},
                navigateToRecipeDetailScreen= {
                    navController.navigate("${IngredientDestination.route}/${it}")
                },
            )
        }
        // Trang Profile, show công thức của nd
        composable(route = ShowRecipeDestination.route){
            RecipePerson(
                recipeAppViewModel = recipeAppViewModel,
                navigateToUpdateRecipe = {
                    navController.navigate("${UpdateRecipeDestination.route}/${it}")
                },
                navigateToRecipeDetailPerson = {
                    navController.navigate("${RecipeDetailPersonDestination.route}/${it}")
                }
            )
        }
        // Trang công thức chi tiết người dùng
        composable(
            route = RecipeDetailPersonDestination.routeWithID,
            arguments = listOf(navArgument(RecipeDetailPersonDestination.idRecipe) {
                type = NavType.IntType
            })
        ){
            RecipeDetailPerson(
                recipeAppViewModel = recipeAppViewModel,
                navigateBack = {navController.popBackStack()}
            )
        }
        // Thêm công thức
        composable(route = AddRecipeDestination.route){
            AddRecipe(
                navigateToShowRecipe = { navController.navigate(ShowRecipeDestination.route) },
            )
        }
        // Cập nhật công thức
        composable(
            route = UpdateRecipeDestination.routeWithID,
            arguments = listOf(navArgument(UpdateRecipeDestination.idRecipe) {
                type = NavType.IntType
            })
        ){
            UpdateRecipe(
                recipeAppViewModel = recipeAppViewModel,
                navigateBack = {navController.popBackStack()},
            )
        }
        // Yêu thích
        composable(route = FavouriteDestination.route){
            RecipeFavoriteScreen(
                navigateToRecipeDetailScreen={
                    navController.navigate("${IngredientDestination.route}/${it}")
                },
                recipeAppViewModel = recipeAppViewModel
            )
        }
        // Chi tiết công thức
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
        // Danh sách mua sắm
        composable(
            route = ShoppingListDestination.route
        ){
            ShoppingListScreen(
                recipeAppViewModel = recipeAppViewModel
            )
        }

        composable(route = ScheduleDestination.route){
            ScheduleScreen(
                recipeAppViewModel = recipeAppViewModel,
                navToFindForSchedule = {navController.navigate(FindForScheduleDestination.route)},
                navToBack = {navController.popBackStack()}
            )
        }

        composable(route = FindForScheduleDestination.route){
            FindForScheduleScreen(
                recipeAppViewModel = recipeAppViewModel,
                navToBack = {navController.popBackStack()}
            )
        }
    }
}
