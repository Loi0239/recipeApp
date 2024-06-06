package com.example.recipeapp.data.dynamic_data.ingredient

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Insert
    suspend fun insertIngredient(ingredient: Ingredient)

    @Query("SELECT * from NguyenLieu WHERE I_id_cong_thuc_nd = :id")
    fun getItemIngredient(id: Int): Flow<List<Ingredient>>

    @Update
    suspend fun updateIngredients(ingredients: List<Ingredient>)
}