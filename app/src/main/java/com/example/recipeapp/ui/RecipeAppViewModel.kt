package com.example.recipeapp.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.recipeapp.RecipeApplication
import com.example.recipeapp.data.static_data.FooterItem
import com.example.recipeapp.data.static_data.FooterRepository
import com.example.recipeapp.ui.favouriteRecipe.FavouriteRecipeViewModel
import com.example.recipeapp.ui.home.HomeViewModel
import com.example.recipeapp.ui.product.AllProductViewModel
import com.example.recipeapp.ui.product.category_product.CateProViewModel
import com.example.recipeapp.ui.product.find_name_product.FindNameViewModel
import com.example.recipeapp.ui.recipe.RecipeViewModel
import com.example.recipeapp.ui.recipe_person.ShowRecipeViewModel
import com.example.recipeapp.ui.recipe_person.add_recipe.AddRecipeViewModel
import com.example.recipeapp.ui.recipe_person.update_recipe.UpdateRecipeViewModel
import com.example.recipeapp.ui.shoppingList.ShoppingListViewModel

class RecipeAppViewModel : ViewModel() {
    var idUiState by mutableStateOf(IdUiState())
        private set

    private val _footerState = mutableStateOf(false)
    val footerState: State<Boolean> = _footerState

    fun setFooterState(newState: Boolean) {
        _footerState.value = newState
    }

    fun updateIdUser(idUser:Int){
        idUiState.idUser = idUser
        Log.i("check", "updateIdUser: ${idUiState.idUser}")
    }

    companion object DeliveryViewModel{
        val Factory = viewModelFactory {
            initializer {
                RecipeAppViewModel()
            }

            initializer {
                HomeViewModel()
            }

            initializer {
                FavouriteRecipeViewModel(
                    recipeApplication().container.favouriteRepository
                )
            }

            initializer {
                AllProductViewModel(
                    recipeApplication().container.favouriteRepository
                )
            }

            initializer {
                RecipeViewModel(
                    this.createSavedStateHandle(),
                    recipeApplication().container.favouriteRepository,
                    recipeApplication().container.shoppingRepository
                )
            }

            initializer {
                ShoppingListViewModel(
                    recipeApplication().container.shoppingRepository
                )
            }

            initializer {
                ShowRecipeViewModel(
                    recipeApplication().container.recipePersonRepository,
                    recipeApplication().container.favouriteRepository
                )
            }

            initializer {
                AddRecipeViewModel(
                    recipeApplication().container.recipePersonRepository,
                    recipeApplication().container.ingredientRepository
                )
            }

            initializer {
                UpdateRecipeViewModel(
                    recipeApplication().container.recipePersonRepository,
                    recipeApplication().container.ingredientRepository,
                    this.createSavedStateHandle()
                )
            }

            initializer {
                RecipeViewModel(
                    this.createSavedStateHandle(),
                    recipeApplication().container.favouriteRepository,
                    recipeApplication().container.shoppingRepository
                )
            }

            initializer {
                CateProViewModel(
                    this.createSavedStateHandle()
                )
            }

            initializer {
                FindNameViewModel(
                    this.createSavedStateHandle()
                )
            }

        }
    }

    //xử lí footer
    private val _footerItems = MutableLiveData<List<FooterItem>>()
    val footerItems: LiveData<List<FooterItem>> get() = _footerItems

    init {
        _footerItems.value = FooterRepository.footerItems
    }

    fun onItemClicked(itemId: Int) {
        val items = _footerItems.value.orEmpty().map {
            it.copy(checked = it.id == itemId)
        }
        _footerItems.value = items
    }
}

data class IdUiState(
    var idUser:Int = 0,
    var idCart:Int = 0,
)

data class UiState(
    var footerState:Boolean = false
)

fun CreationExtras.recipeApplication(): RecipeApplication =
    (this[APPLICATION_KEY] as RecipeApplication)