package com.example.recipeapp.data.static_data

data class Category(
    val id:Int,
    val name:String,
)

class Categories{
    var listCategory = mutableListOf(
        Category(
            id = 0,
            name = "Món ăn mặn"
        ),
        Category(
            id = 1,
            name = "Món ăn chay"
        ),
        Category(
            id = 1,
            name = "Món xào"
        ),
        Category(
            id = 2,
            name = "Món chiên"
        ),
        Category(
            id = 2,
            name = "Món luộc"
        ),
    )

    public fun getCategoryList(): MutableList<Category> {
        return listCategory
    }
}


