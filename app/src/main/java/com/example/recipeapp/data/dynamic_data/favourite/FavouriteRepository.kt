package com.example.recipeapp.data.dynamic_data.favourite

import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun insertFavourite(favourite: Favourite)

    suspend fun deleteFavourite(favourite: Favourite)

    suspend fun checkFavourite(idProduct:Int):Int

    suspend fun getIdFavourite(idProduct: Int):Int

    fun selectFavourite():Flow<List<Favourite>>
}