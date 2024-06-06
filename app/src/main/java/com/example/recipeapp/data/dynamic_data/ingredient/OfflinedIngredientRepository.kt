package com.example.recipeapp.data.dynamic_data.ingredient

import kotlinx.coroutines.flow.Flow

class OfflinedIngredientRepository(private val ingredientDao: IngredientDao): IngredientRepository {
    override suspend fun insertIngredient(ingredient: Ingredient) =
        ingredientDao.insertIngredient(ingredient)

    override fun getItemIngredient(id: Int): Flow<List<Ingredient>> =
        ingredientDao.getItemIngredient(id)

    override suspend fun updateIngredients(ingredients: List<Ingredient>) =
        ingredientDao.updateIngredients(ingredients)

}