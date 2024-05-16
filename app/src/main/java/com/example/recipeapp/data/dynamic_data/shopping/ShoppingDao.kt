package com.example.recipeapp.data.dynamic_data.shopping

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface ShoppingDao {
    @Insert
    suspend fun insertShopping(shopping: Shopping)

    @Delete
    suspend fun deleteShopping(shopping: Shopping)

    @Query("select I_id_mua_sam from muasam where I_id_san_pham = :idProduct")
    suspend fun getIdShopping(idProduct:Int):Int

    @Query("select * from muasam order by I_id_mua_sam desc")
    fun selectShopping(): Flow<List<Shopping>>
}