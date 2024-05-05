package com.example.recipeapp.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.recipeapp.RecipeApplication
import com.example.recipeapp.data.static_data.FooterItem
import com.example.recipeapp.data.static_data.FooterRepository
import com.example.recipeapp.ui.login.LoginViewModel

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
                LoginViewModel(
                    recipeApplication().container.acc
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