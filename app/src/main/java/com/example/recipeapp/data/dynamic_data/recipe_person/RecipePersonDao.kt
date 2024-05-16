package com.example.recipeapp.data.dynamic_data.recipe_person

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipePersonDao {
    @Insert
    suspend fun insertRecipePerson(recipePerson: RecipePerson)

    @Query("SELECT * FROM CongThucNguoiDung")
    fun getAll(): Flow<List<RecipePerson>>

    @Delete
    suspend fun deleteRecipePerson(recipePerson: RecipePerson)

    @Query("SELECT * from CongThucNguoiDung WHERE I_id_cong_thuc_nd = :id")
    fun getItemRecipeStream(id: Int): Flow<RecipePerson>
    @Update
    suspend fun updateRecipePerson(recipePerson: RecipePerson)
    @Query("SELECT I_id_cong_thuc_nd FROM CongThucNguoiDung ORDER BY I_id_cong_thuc_nd DESC LIMIT 1")
    suspend fun getLastInsertId(): Int

}