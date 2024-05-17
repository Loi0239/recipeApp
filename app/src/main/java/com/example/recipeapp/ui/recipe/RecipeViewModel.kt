package com.example.recipeapp.ui.recipe

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.dynamic_data.favourite.Favourite
import com.example.recipeapp.data.dynamic_data.favourite.FavouriteRepository
import com.example.recipeapp.data.dynamic_data.shopping.Shopping
import com.example.recipeapp.data.dynamic_data.shopping.ShoppingRepository
import kotlinx.coroutines.launch

class RecipeViewModel(
    savedStateHandle: SavedStateHandle,
    private val favouriteRepository: FavouriteRepository,
    private val shoppingRepository: ShoppingRepository,
):ViewModel() {
    val productId: Int = checkNotNull(savedStateHandle[IngredientDestination.productId])
    var uiState by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            // Gọi hàm checkFavourite để kiểm tra xem sản phẩm có trong danh sách favorite không
            val isFavorite = checkFavourite()
            // Cập nhật giá trị checkFavorite trong uiState
            uiState = uiState.copy(checkFavorite = isFavorite)
        }
        checkShopping()
    }

    suspend fun addFavourite(){
        favouriteRepository.insertFavourite(uiState.favouriteState.addFavourite(productId))
    }

    suspend fun deleteFavourite(){
        val idFavourite = favouriteRepository.getIdFavourite(productId)
        favouriteRepository.deleteFavourite(
            uiState.favouriteState.deleteFavourite(idFavourite,productId))
    }

    suspend fun checkFavourite():Int{
        return favouriteRepository.checkFavourite(productId)
    }

    fun updateCheckFavourite(){
        viewModelScope.launch {
            uiState = uiState.copy(checkFavorite = checkFavourite())
        }
    }

    fun addShopping(){
        viewModelScope.launch {
            shoppingRepository.insertShopping(
                uiState.shoppingState.addShopping(productId))
            //thêm một hàm kiểm tra đã có hay chưa ở đây
            checkShopping()
        }
    }

    private fun checkShopping(){
        viewModelScope.launch {
            val checkShopping = shoppingRepository.getIdShopping(productId)
            uiState = if(checkShopping>0){
                uiState.copy(checkAddShopping = false)
            }else{
                uiState.copy(checkAddShopping = true)
            }
            Log.i("checkAdd1", "${uiState.checkAddShopping}")
            Log.i("checkAdd2", "$checkShopping")
            Log.i("checkAdd3", "$productId")
        }
    }
}

data class UiState(
    val favouriteState: FavouriteState = FavouriteState(),
    val shoppingState: ShoppingState = ShoppingState(),
    var checkFavorite:Int = 0,
    val checkAddShopping: Boolean = true
)

data class FavouriteState(
    var id:Int = 0,
    var idProduct:Int = 0,
)

private fun FavouriteState.addFavourite(productId:Int): Favourite = Favourite(
    id = id,
    idProduct = productId
)

private fun FavouriteState.deleteFavourite(idFavourite:Int, idProduct:Int): Favourite = Favourite(
    id = idFavourite,
    idProduct = idProduct
)

data class ShoppingState(
    var id: Int = 0,
    var idProduct:Int = 0,
)

private fun ShoppingState.addShopping(productId: Int): Shopping = Shopping(
    id = id,
    idProduct = productId
)