package com.example.recipeapp.ui.shoppingList

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.dynamic_data.shopping.Shopping
import com.example.recipeapp.data.dynamic_data.shopping.ShoppingRepository
import com.example.recipeapp.data.static_data.Ingredient
import com.example.recipeapp.data.static_data.Products
import kotlinx.coroutines.launch

class ShoppingListViewModel(
    private val shoppingRepository: ShoppingRepository
):ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    //danh sách công thức được chọn đi mua
    private val _productList = MutableLiveData<List<Int>>()
    val productList: LiveData<List<Int>> get() = _productList
    //danh sách các nguyên liệu tổng hợp từ danh sách công thức
    private val _ingredientList = MutableLiveData<List<Ingredient>>()
    val ingredientList: LiveData<List<Ingredient>> get() = _ingredientList

    //danh sách các công thức muôn lưu trữ đi mua
    private val _shoppingList = MutableLiveData<List<Shopping>>()
    val shoppingList: LiveData<List<Shopping>> get() = _shoppingList

    //danh sách trạng thái đã mua của từng loại nguyên liệu
    private val _completeList = MutableLiveData<List<Boolean>>()
    val completeList: LiveData<List<Boolean>> get() = _completeList

    init {
        updateShoppingList()
    }

    fun changeCheckStatePage(){
        uiState = uiState.copy(checkStatePage = !uiState.checkStatePage)
    }

    fun updateShoppingList(){
        viewModelScope.launch {
            shoppingRepository.selectShopping().collect { shoppings ->
                _shoppingList.value = shoppings
            }
        }
    }

    fun deleteShoppingList(idProduct: Int){
        viewModelScope.launch {
            val idShopping = shoppingRepository.getIdShopping(idProduct)
            shoppingRepository.deleteShopping(
                uiState.shopping.deleteShopping(idShopping,idProduct))
            updateShoppingList()
        }
    }


    fun addListSelect(idProduct: Int) {
        val currentList = _productList.value.orEmpty().toMutableList()
        if (!currentList.contains(idProduct)) {
            currentList.add(idProduct)
            _productList.value = currentList
        }
        addListIngredient(idProduct)
    }

    fun deleteListSelect(idProduct: Int) {
        val currentList = _productList.value.orEmpty().toMutableList()
        if (currentList.contains(idProduct)) {
            currentList.remove(idProduct)
            _productList.value = currentList
        }
        deleteListIngredient(idProduct)
    }

    fun checkListSelect(idProduct: Int): Boolean {
        return _productList.value.orEmpty().contains(idProduct)
    }

    fun completeShopping() {
        val currentList = _productList.value.orEmpty()
        for (idProduct in currentList) {
            deleteShoppingList(idProduct)
        }
        _productList.value = emptyList()
        _ingredientList.value = emptyList()
        _completeList.value = emptyList()
    }

    fun addListIngredient(idProduct: Int){
        val ingredients = Products().getIngredient(idProduct)
        val currentIngredients = _ingredientList.value.orEmpty().toMutableList()
        for(ingredient in ingredients){
            val existingIngredient = currentIngredients.find { it.id == ingredient.id }
            if (existingIngredient != null) {
                val newQuantity = calculateQuantities(existingIngredient.quantity, ingredient.quantity, true)
                existingIngredient.quantity = newQuantity
            } else {
                currentIngredients.add(ingredient)
            }
        }
        _ingredientList.value = currentIngredients
        createElemetForCompleteList()
    }

    fun deleteListIngredient(idProduct: Int) {
        val ingredients = Products().getIngredient(idProduct)
        val currentIngredients = _ingredientList.value.orEmpty().toMutableList()
        val currentCompletes = _completeList.value.orEmpty().toMutableList()

        for (ingredient in ingredients) {
            val index = currentIngredients.indexOfFirst { it.id == ingredient.id }
            if (index != -1) {
                val existingIngredient = currentIngredients[index]
                val newQuantity = calculateQuantities(existingIngredient.quantity, ingredient.quantity, false)
                existingIngredient.quantity = newQuantity

                if (newQuantity.startsWith("0")) {
                    // Xóa phần tử tại vị trí index trong cả hai danh sách
                    currentIngredients.removeAt(index)
                    currentCompletes.removeAt(index)
                }
            }
        }
        _ingredientList.value = currentIngredients
        _completeList.value = currentCompletes
    }


    // Hàm để đảm bảo completeList có số phần tử bằng ingredientList
    fun createElemetForCompleteList() {
        val ingredients = _ingredientList.value ?: emptyList()
        val completeStatuses = _completeList.value?.toMutableList() ?: mutableListOf()

        // Thêm các phần tử `false` nếu cần
        while (completeStatuses.size < ingredients.size) {
            completeStatuses.add(false)
        }

        // Cập nhật giá trị của _completeList
        _completeList.value = completeStatuses
        Log.i("completeList", "${completeList.value}")
        Log.i("ingredientList", "${ingredientList.value}")
    }

    fun updateCompleteList(index:Int){
        // Lấy danh sách hiện tại, nếu null thì tạo danh sách rỗng
        val currentList = _completeList.value?.toMutableList() ?: mutableListOf()

        // Kiểm tra chỉ mục hợp lệ
        if (index in currentList.indices) {
            // Đảo ngược giá trị tại chỉ mục
            currentList[index] = !currentList[index]
            // Cập nhật lại giá trị vào LiveData
            _completeList.value = currentList
        } else {
            // Xử lý nếu chỉ mục không hợp lệ, ví dụ như log hoặc throw exception
            Log.e("YourViewModel", "Index out of bounds: $index")
        }
    }

    private fun calculateQuantities(quantity1: String, quantity2: String, sign:Boolean): String {
        val (number1, unit1) = parseQuantity(quantity1)
        val (number2, unit2) = parseQuantity(quantity2)

        if (unit1 != unit2) {
            throw IllegalArgumentException("Đơn vị không giống nhau: $unit1 và $unit2")
        }

        val result = if(sign){
            number1 + number2
        }else{
            number1 - number2
        }
        return "$result $unit1"
    }

    private fun parseQuantity(input: String): Pair<Int, String> {
        val parts = input.split(" ")
        val numberString = parts.firstOrNull()?.toIntOrNull() ?: 0
        val unit = parts.drop(1).joinToString(" ").trim()
        return Pair(numberString, unit)
    }
}

data class UiState(
    val shopping: ShoppingState = ShoppingState(),
    var checkStatePage:Boolean = true
)

data class ShoppingState(
    val id:Int = 0,
    val idProduct: Int = 0,
)

private fun ShoppingState.deleteShopping(
    idShopping:Int,idProduct:Int):Shopping = Shopping(
    id = idShopping,
    idProduct = idProduct
)