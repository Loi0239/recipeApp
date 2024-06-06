package com.example.recipeapp.ui.product.all_product

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.dynamic_data.favourite.Favourite
import com.example.recipeapp.data.dynamic_data.favourite.FavouriteRepository
import kotlinx.coroutines.launch

class AllProductViewModel (private val favouriteRepository: FavouriteRepository): ViewModel(){
    var uiState by mutableStateOf(UiState())
        private set

    private val _favouriteList = MutableLiveData<List<Favourite>>()
    val favouriteList: LiveData<List<Favourite>> get() = _favouriteList

    init {
        viewModelScope.launch {
            updateFavoriteList()
        }

    }

    suspend fun updateFavoriteList(){
        viewModelScope.launch {
            favouriteRepository.selectFavourite().collect { favourites ->
                _favouriteList.value = favourites
            }
        }.join()
    }

    fun fillFavourite(favouriteList: List<Favourite>,idProduct: Int):Boolean{
        for (favorite in favouriteList) {
            Log.i("ok", "${favorite.idProduct}")
            if (favorite.idProduct == idProduct) {
                // Xử lý khi tìm thấy sản phẩm yêu thích
                return true
            }
        }
        return false
    }

    suspend fun addFavourite(productId: Int){
        favouriteRepository.insertFavourite(uiState.favouriteState.addFavourite(productId))
    }

    suspend fun deleteFavourite(idFavourite: Int, idProduct: Int){
        favouriteRepository.deleteFavourite(
            uiState.favouriteState.deleteFavourite(idFavourite,idProduct))
    }

    suspend fun getIdFavourite(productId: Int):Int{
        return favouriteRepository.getIdFavourite(productId)
    }
}

data class UiState(
    val favouriteState: FavouriteState = FavouriteState(),
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