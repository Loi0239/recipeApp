package com.example.recipeapp.ui.product.find_name_product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class FindNameViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel(){
    val keyproName: String = checkNotNull(savedStateHandle[FindNameProScreenDestination.keyproductName])
}