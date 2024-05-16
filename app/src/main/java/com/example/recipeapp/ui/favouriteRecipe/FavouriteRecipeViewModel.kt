package com.example.recipeapp.ui.favouriteRecipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.dynamic_data.favourite.Favourite
import com.example.recipeapp.data.dynamic_data.favourite.FavouriteRepository
import com.example.recipeapp.data.static_data.Categories
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import kotlinx.coroutines.launch

class FavouriteRecipeViewModel(
    private val favouriteRepository: FavouriteRepository
): ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    private val _favouriteList = MutableLiveData<List<Favourite>>()
    val favouriteList: LiveData<List<Favourite>> get() = _favouriteList

    val categories = Categories().getCategoryList()
    val allCategory = com.example.recipeapp.data.static_data.Category(-1, "Tất cả")
    val updatedCategories = listOf(allCategory) + categories

    init {
        updateFavoriteList()
    }

    suspend fun deleteFavourite(idFavourite: Int, idProduct: Int){
        favouriteRepository.deleteFavourite(
            uiState.favouriteState.deleteFavourite(idFavourite,idProduct))
        updateFavoriteList()
    }

    fun updateFavoriteList(){
        viewModelScope.launch {
            favouriteRepository.selectFavourite().collect { favourites ->
                _favouriteList.value = favourites
            }
        }
    }

    fun getProduct(id:Int):Product{
        return Products().productList[id]
    }
}

data class UiState(
    val favouriteState: FavouriteState = FavouriteState(),
    var checkFavorite:Int = 0,
)

data class FavouriteState(
    var id:Int = 0,
    var idProduct:Int = 0,
)

private fun FavouriteState.deleteFavourite(idFavourite:Int, idProduct:Int): Favourite = Favourite(
    id = idFavourite,
    idProduct = idProduct
)
