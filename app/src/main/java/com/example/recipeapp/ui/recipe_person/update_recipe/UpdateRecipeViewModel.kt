package com.example.recipeapp.ui.recipe_person.update_recipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.dynamic_data.ingredient.Ingredient
import com.example.recipeapp.data.dynamic_data.ingredient.IngredientRepository
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePerson
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePersonRepository
import com.example.recipeapp.ui.recipe_person.add_recipe.UiState
import com.example.recipeapp.ui.recipe_person.add_recipe.UiStateRecipe
import kotlinx.coroutines.launch

class UpdateRecipeViewModel(
    private val recipePersonRepository: RecipePersonRepository,
    private val ingredientRepository: IngredientRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val idRecipe: Int = checkNotNull(savedStateHandle[UpdateRecipeDestination.idRecipe])

    var uiState by mutableStateOf(UiState())
        private set

    var recipePerson by mutableStateOf<RecipePerson?>(null)
        private set

    var ingredient by mutableStateOf<List<Ingredient>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            recipePersonRepository.getItemRecipeStream(idRecipe).collect {
                recipePerson = it
            }
        }
    }

    init {
        viewModelScope.launch {
            ingredientRepository.getItemIngredient(idRecipe).collect {
                ingredient = it
            }
        }
    }

    suspend fun updateRecipe(updatedRecipe: RecipePerson) {
        recipePerson?.let { recipePerson ->
            recipePersonRepository.updateRecipePerson(updatedRecipe)
        }
    }

    suspend fun updateIngredients(updatedIngredients: List<Ingredient>) {
        ingredientRepository.updateIngredients(updatedIngredients)
    }

    fun updateUiState(uiStateRecipe: UiStateRecipe){
        uiState = UiState(uiStateRecipe = uiStateRecipe)
    }

    fun updateIngredientName(index: Int, newName: String) {
        val currentIngredients = ingredient.toMutableList()
        if (index in currentIngredients.indices) {
            val updatedIngredient = currentIngredients[index].copy(nameIngre = newName)
            currentIngredients[index] = updatedIngredient
            ingredient = currentIngredients
        }
    }

    fun updateIngredientWeight(index: Int, newWeight: String) {
        val currentIngredients = ingredient.toMutableList()
        if (index in currentIngredients.indices) {
            val updatedIngredient = currentIngredients[index].copy(weightIngre = newWeight)
            currentIngredients[index] = updatedIngredient
            ingredient = currentIngredients
        }
    }
}
private fun RecipePerson.updateRecipe(uiStateRecipe: UiStateRecipe): RecipePerson {
    return copy(
        nameRecipe = uiStateRecipe.nameRecipe,
        time = uiStateRecipe.time,
        step = uiStateRecipe.step
    )
}



