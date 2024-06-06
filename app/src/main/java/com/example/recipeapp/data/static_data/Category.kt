package com.example.recipeapp.data.static_data

data class Category(
    val id:Int,
    val name:String,
)

class Categories{
    var listCategory = mutableListOf(
        Category(
            id = 0,
            name = "Món khai vị"
        ),
        Category(
            id = 1,
            name = "Món chính"
        ),
        Category(
            id = 2,
            name = "Món tráng miệng"
        ),
        Category(
            id = 3,
            name = "Món ăn sáng"

        ),
        Category(
            id = 4,
            name = "Đồ nướng"
        ),
        Category(
            id = 5,
            name = "Món rau củ"
        ),
        Category(
            id = 6,
            name = "Món canh"
        ),
    )

    fun getCategoryList(): MutableList<Category> {
        return listCategory
    }
}


