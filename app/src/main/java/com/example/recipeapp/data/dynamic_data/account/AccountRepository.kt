package com.example.recipeapp.data.dynamic_data.account

import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAccount(id:Int): Flow<Account>

    fun checkAccount(userName:String, password:String):Int

    fun checkUserName(userName: String):Int

    fun getIdUser(userName:String, password:String): Int

    suspend fun getLastInsertId():Int

    suspend fun insertAccount(account: Account)
}