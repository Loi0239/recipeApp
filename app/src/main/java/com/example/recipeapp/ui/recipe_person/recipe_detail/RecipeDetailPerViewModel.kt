package com.example.recipeapp.ui.recipe_person.recipe_detail

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
import kotlinx.coroutines.launch

class RecipeDetailPerViewModel(
    private val recipePersonRepository: RecipePersonRepository,
    private val ingredientRepository: IngredientRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val idRecipe: Int = checkNotNull(savedStateHandle[RecipeDetailPersonDestination.idRecipe])

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
}