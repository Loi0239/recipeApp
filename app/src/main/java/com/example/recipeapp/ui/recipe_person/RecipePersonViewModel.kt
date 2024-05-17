package com.example.recipeapp.ui.recipe_person

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.dynamic_data.favourite.FavouriteRepository
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePerson
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePersonRepository
import com.example.recipeapp.ui.recipe_person.add_recipe.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ShowRecipeViewModel(
    private val recipePersonRepository: RecipePersonRepository,
    private val favouriteRepository: FavouriteRepository
): ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    private val _countRecipeState = MutableStateFlow(0)
    val countRecipeState: StateFlow<Int> = _countRecipeState

    private val _countFavourState = MutableStateFlow(0)
    val countFavourState: StateFlow<Int> = _countFavourState

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

    init {
        getCount()
    }

    private fun getCount() {
        viewModelScope.launch {
            _countRecipeState.value = recipePersonRepository.getCount()
        }
    }

    init {
        getCountFavourite()
    }

    private fun getCountFavourite() {
        viewModelScope.launch {
            _countFavourState.value = favouriteRepository.getCountFavourite()
        }
    }

    suspend fun deleteRecipe(recipePerson: RecipePerson) {
        recipePersonRepository.deleteRecipePerson(recipePerson)
        getCount()
    }

//    fun updateUiState(uiStateRecipe: UiStateRecipe){
//        uiState = UiState(uiStateRecipe = uiStateRecipe)
//    }
}
data class showDataState(val itemList: List<RecipePerson> = listOf())
