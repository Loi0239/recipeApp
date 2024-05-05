package com.example.recipeapp.data

import android.content.Context
import com.example.recipeapp.data.dynamic_data.account.AccountRepository
import com.example.recipeapp.data.dynamic_data.account.OfflineAccountRepository

interface AppContainer{
    val accountsRepository: AccountRepository
}

class AppDataContainer(private val context: Context): AppContainer{
    override val accountsRepository: AccountRepository by lazy {
        OfflineAccountRepository(RecipeDatabase.getDatabase(context).accountDao())
    }
}