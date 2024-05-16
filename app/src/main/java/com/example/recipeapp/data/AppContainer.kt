package com.example.recipeapp.data

import android.content.Context
import com.example.recipeapp.data.dynamic_data.account.AccountRepository
import com.example.recipeapp.data.dynamic_data.account.OfflineAccountRepository
import com.example.recipeapp.data.dynamic_data.favourite.FavouriteRepository
import com.example.recipeapp.data.dynamic_data.favourite.OfflineFavouriteRepository
import com.example.recipeapp.data.dynamic_data.ingredient.IngredientRepository
import com.example.recipeapp.data.dynamic_data.ingredient.OfflinedIngredientRepository
import com.example.recipeapp.data.dynamic_data.recipe_person.OfflineRecipePRepository
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePersonRepository
import com.example.recipeapp.data.dynamic_data.shopping.OfflineShoppingRepository
import com.example.recipeapp.data.dynamic_data.shopping.ShoppingRepository

interface AppContainer{
    val accountsRepository: AccountRepository
    val favouriteRepository: FavouriteRepository
    val shoppingRepository: ShoppingRepository
    val recipePersonRepository: RecipePersonRepository
    val ingredientRepository : IngredientRepository
}

class AppDataContainer(private val context: Context): AppContainer{
    override val accountsRepository: AccountRepository by lazy {
        OfflineAccountRepository(RecipeDatabase.getDatabase(context).accountDao())
    }
    override val favouriteRepository: FavouriteRepository by lazy {
        OfflineFavouriteRepository(RecipeDatabase.getDatabase(context).favouriteDao())
    }
    override val shoppingRepository: ShoppingRepository by lazy {
        OfflineShoppingRepository(RecipeDatabase.getDatabase(context).shoppingDao())
    }
    override val recipePersonRepository: RecipePersonRepository by lazy {
        OfflineRecipePRepository(RecipeDatabase.getDatabase(context).recipePersonDao())
    }
    override val ingredientRepository: IngredientRepository by lazy {
        OfflinedIngredientRepository(RecipeDatabase.getDatabase(context).ingredientDao())
    }
}