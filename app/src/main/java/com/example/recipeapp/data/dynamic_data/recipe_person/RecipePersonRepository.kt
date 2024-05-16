package com.example.recipeapp.data.dynamic_data.recipe_person

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface RecipePersonRepository {
    suspend fun insertRecipePerson(recipePerson: RecipePerson)
    suspend fun getLastInsertId(): Int
    fun getAll(): Flow<List<RecipePerson>>

    suspend fun deleteRecipePerson(recipePerson: RecipePerson)

    fun getItemRecipeStream(id: Int): Flow<RecipePerson?>
    suspend fun updateRecipePerson(recipePerson: RecipePerson)
}