package com.example.recipeapp.ui.recipe_person.add_recipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.dynamic_data.ingredient.Ingredient
import com.example.recipeapp.data.dynamic_data.ingredient.IngredientRepository
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePerson
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePersonRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddRecipeViewModel(
    private val recipePersonRepository: RecipePersonRepository,
    private val ingredientRepository: IngredientRepository
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

//    fun updateUiState(uiStateRecipe: UiStateRecipe, uiNguyenLieu: UINguyenLieu){
//        uiState =
//            UiState(
//                uiStateRecipe = uiStateRecipe,
//                uiNguyenLieu = uiNguyenLieu
//            )
//    }
    fun updateUiState(uiStateRecipe: UiStateRecipe) {
        uiState = uiState.copy(uiStateRecipe = uiStateRecipe)
    }
//    suspend fun addNguyenLieu(id: Int){
//        viewModelScope.launch {
//            ingredientRepository.insertIngredient(uiState.uiNguyenLieu.addNguyenLieu(id))
//        }
//    }
    fun addIngredient(nameIngre: String, weightIngre: String) {
//        val ingredients = uiState.ingredients.toMutableList()
//        ingredients.add(UINguyenLieu(nameIngre = nameIngre, weightIngre = weightIngre))
//        uiState = uiState.copy(ingredients = ingredients)
    val newIngredient = UINguyenLieu(nameIngre = nameIngre, weightIngre = weightIngre)
    val updatedIngredients = uiState.ingredients.toMutableList().apply {
        add(newIngredient)
    }
    uiState = uiState.copy(ingredients = updatedIngredients)
    }
//    fun addRecipe() {
//        viewModelScope.launch {
//            recipePersonRepository.insertRecipePerson(uiState.uiStateRecipe.addRecipe())
//            val id = recipePersonRepository.getLastInsertId()
//            addNguyenLieu(id)
//        }
//    }
    fun addRecipe() {
        viewModelScope.launch {
            recipePersonRepository.insertRecipePerson(uiState.uiStateRecipe.addRecipe())
            val id = recipePersonRepository.getLastInsertId()
            uiState.ingredients.forEach { ingredientUiState ->
                ingredientRepository.insertIngredient(ingredientUiState.addNguyenLieu(id))
            }
        }
    }
}
data class showDataState(val itemList: List<RecipePerson> = listOf())

data class UiState(
    val uiStateRecipe: UiStateRecipe = UiStateRecipe(),
//    val uiNguyenLieu : UINguyenLieu = UINguyenLieu()
    val ingredients: List<UINguyenLieu> = listOf()
)

data class UiStateRecipe(
    var id: Int = 0,
    var nameRecipe: String = "",
    var time: String = "",
    var step:String = "",
)
private fun UiStateRecipe.addRecipe(): RecipePerson = RecipePerson(
    id = id,
    nameRecipe = nameRecipe,
    time = time,
    step = step
)
data class UINguyenLieu(
    var id: Int=0,
    var nameIngre:String ="",
    var weightIngre : String="",
    var idRecPer:Int=0
)
private fun UINguyenLieu.addNguyenLieu(idRecPer:Int): Ingredient = Ingredient(
    id = id,
    nameIngre = nameIngre,
    weightIngre = weightIngre,
    idRecPer = idRecPer,
)
