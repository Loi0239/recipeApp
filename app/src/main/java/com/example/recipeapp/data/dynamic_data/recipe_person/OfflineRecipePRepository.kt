package com.example.recipeapp.data.dynamic_data.recipe_person

import kotlinx.coroutines.flow.Flow

class OfflineRecipePRepository(private val recipePersonDao: RecipePersonDao):RecipePersonRepository {
    override suspend fun insertRecipePerson(recipePerson: RecipePerson) =
        recipePersonDao.insertRecipePerson(recipePerson)

    override fun getAll(): Flow<List<RecipePerson>> = recipePersonDao.getAll()

    override suspend fun deleteRecipePerson(recipePerson: RecipePerson) =
        recipePersonDao.deleteRecipePerson(recipePerson)

    override fun getItemRecipeStream(id: Int): Flow<RecipePerson> =
        recipePersonDao.getItemRecipeStream(id)

    override suspend fun updateRecipePerson(recipePerson: RecipePerson) =
        recipePersonDao.updateRecipePerson(recipePerson)

    override suspend fun getLastInsertId():Int = recipePersonDao.getLastInsertId()

    override suspend fun getCount(): Int = recipePersonDao.getCount()
}