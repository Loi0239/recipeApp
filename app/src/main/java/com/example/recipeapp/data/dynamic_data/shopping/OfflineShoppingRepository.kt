package com.example.recipeapp.data.dynamic_data.shopping

import kotlinx.coroutines.flow.Flow

class OfflineShoppingRepository(private val shoppingDao: ShoppingDao):ShoppingRepository {
    override suspend fun insertShopping(shopping: Shopping) =
        shoppingDao.insertShopping(shopping)

    override suspend fun deleteShopping(shopping: Shopping) =
        shoppingDao.deleteShopping(shopping)

    override suspend fun getIdShopping(idProduct: Int): Int =
        shoppingDao.getIdShopping(idProduct)

    override fun selectShopping(): Flow<List<Shopping>> =
        shoppingDao.selectShopping()
}