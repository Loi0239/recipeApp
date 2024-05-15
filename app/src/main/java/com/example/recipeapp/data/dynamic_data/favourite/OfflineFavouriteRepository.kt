package com.example.recipeapp.data.dynamic_data.favourite

import kotlinx.coroutines.flow.Flow

class OfflineFavouriteRepository(private val favouriteDao: FavouriteDao): FavouriteRepository {
    override suspend fun insertFavourite(favourite: Favourite) =
        favouriteDao.insertFavourite(favourite)

    override suspend fun deleteFavourite(favourite: Favourite) =
        favouriteDao.deleteFavourite(favourite)

    override suspend fun checkFavourite(idProduct: Int): Int =
        favouriteDao.checkFavourite(idProduct)

    override suspend fun getIdFavourite(idProduct: Int): Int =
        favouriteDao.getIdFavourite(idProduct)

    override fun selectFavourite(): Flow<List<Favourite>> =
        favouriteDao.selectFavourite()
}