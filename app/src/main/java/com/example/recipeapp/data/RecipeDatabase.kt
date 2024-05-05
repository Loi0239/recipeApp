package com.example.recipeapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipeapp.data.dynamic_data.account.Account
import com.example.recipeapp.data.dynamic_data.account.AccountDao

@Database(
    entities = [
        Account::class,
    ], version = 1,
    exportSchema = true)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao

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