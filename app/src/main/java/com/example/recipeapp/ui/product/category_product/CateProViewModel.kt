package com.example.recipeapp.ui.product.category_product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.dynamic_data.favourite.FavouriteRepository

class CateProViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    val cateId: Int = checkNotNull(savedStateHandle[CategoryProductScreenDestination.categoryId])
}