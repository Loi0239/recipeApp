package com.example.recipeapp.ui.schedule.findingForSchedule

import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products

class FindForScheduleViewModel():ViewModel(){
    fun getListProduct(searchWord:String): List<Product>{
        return Products().getProductsByName(searchWord)
    }
}