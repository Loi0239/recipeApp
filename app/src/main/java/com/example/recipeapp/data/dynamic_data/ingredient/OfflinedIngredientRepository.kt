package com.example.recipeapp.data.dynamic_data.ingredient

import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePerson
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePersonDao
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePersonRepository
import kotlinx.coroutines.flow.Flow

class OfflinedIngredientRepository(private val ingredientDao: IngredientDao): IngredientRepository {
    override suspend fun insertIngredient(ingredient: Ingredient) = ingredientDao.insertIngredient(ingredient)
    override fun getItemIngredient(id: Int): Flow<List<Ingredient>> = ingredientDao.getItemIngredient(id)
    override suspend fun updateIngredients(ingredients: List<Ingredient>) = ingredientDao.updateIngredients(ingredients)

}