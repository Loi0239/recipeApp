package com.example.recipeapp.ui.recipe_person

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePerson
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePersonRepository
import com.example.recipeapp.ui.recipe_person.add_recipe.UiState
import com.example.recipeapp.ui.recipe_person.add_recipe.UiStateRecipe
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
class ShowRecipeViewModel(
    private val recipePersonRepository: RecipePersonRepository,
): ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set
    val showDataState: StateFlow<showDataState> =
        recipePersonRepository.getAll().map { showDataState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = showDataState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    suspend fun deleteRecipe(recipePerson: RecipePerson) {
        recipePersonRepository.deleteRecipePerson(recipePerson)
    }
    fun updateUiState(uiStateRecipe: UiStateRecipe){
        uiState = UiState(uiStateRecipe = uiStateRecipe)
    }
}
data class showDataState(val itemList: List<RecipePerson> = listOf())
