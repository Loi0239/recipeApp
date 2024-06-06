package com.example.recipeapp.data.dynamic_data.ingredient

import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    suspend fun insertIngredient(ingredient: Ingredient)

    fun getItemIngredient(id: Int): Flow<List<Ingredient>>

    suspend fun updateIngredients(ingredients: List<Ingredient>)
}