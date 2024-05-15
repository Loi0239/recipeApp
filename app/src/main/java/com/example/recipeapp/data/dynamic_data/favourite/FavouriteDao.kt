package com.example.recipeapp.data.dynamic_data.favourite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Insert
    suspend fun insertFavourite(favourite: Favourite)

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    @Query("select count(*) from yeuthich where I_id_san_pham = :idProduct")
    suspend fun checkFavourite(idProduct:Int):Int

    @Query("select I_id_yeu_thich from yeuthich where I_id_san_pham = :idProduct")
    suspend fun getIdFavourite(idProduct:Int):Int

    @Query("select * from yeuthich order by I_id_yeu_thich desc")
    fun selectFavourite(): Flow<List<Favourite>>
}