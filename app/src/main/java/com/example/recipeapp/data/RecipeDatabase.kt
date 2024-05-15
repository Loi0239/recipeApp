package com.example.recipeapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipeapp.data.dynamic_data.account.Account
import com.example.recipeapp.data.dynamic_data.account.AccountDao
import com.example.recipeapp.data.dynamic_data.favourite.Favourite
import com.example.recipeapp.data.dynamic_data.favourite.FavouriteDao
import com.example.recipeapp.data.dynamic_data.shopping.Shopping
import com.example.recipeapp.data.dynamic_data.shopping.ShoppingDao

@Database(
    entities = [
        Account::class,
        Favourite::class,
        Shopping::class,
    ], version = 3,
    exportSchema = true)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun favouriteDao(): FavouriteDao
    abstract fun shoppingDao(): ShoppingDao


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