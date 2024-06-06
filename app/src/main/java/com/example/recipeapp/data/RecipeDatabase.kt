package com.example.recipeapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.data.dynamic_data.favourite.Favourite
import com.example.recipeapp.data.dynamic_data.favourite.FavouriteDao
import com.example.recipeapp.data.dynamic_data.ingredient.Ingredient
import com.example.recipeapp.data.dynamic_data.ingredient.IngredientDao
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePerson
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePersonDao
import com.example.recipeapp.data.dynamic_data.schedule.Schedule
import com.example.recipeapp.data.dynamic_data.schedule.ScheduleDao
import com.example.recipeapp.data.dynamic_data.shopping.Shopping
import com.example.recipeapp.data.dynamic_data.shopping.ShoppingDao

@Database(
    entities = [
        Favourite::class,
        Shopping::class,
        RecipePerson::class,
        Ingredient::class,
        Schedule::class,
    ], version = 3,
    exportSchema = true)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
    abstract fun shoppingDao(): ShoppingDao
    abstract fun recipePersonDao(): RecipePersonDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun scheduleDao(): ScheduleDao


    companion object{
        @Volatile
        private var Instance: RecipeDatabase ?= null

        fun getDatabase(context: Context): RecipeDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, RecipeDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}