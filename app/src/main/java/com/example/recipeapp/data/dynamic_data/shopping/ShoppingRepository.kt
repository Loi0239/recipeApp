package com.example.recipeapp.data.dynamic_data.shopping

import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {
    suspend fun insertShopping(shopping: Shopping)

    suspend fun deleteShopping(shopping: Shopping)

    suspend fun getIdShopping(idProduct:Int):Int
    fun selectShopping():Flow<List<Shopping>>
}