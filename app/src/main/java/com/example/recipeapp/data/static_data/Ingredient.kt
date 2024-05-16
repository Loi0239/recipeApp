package com.example.recipeapp.data.static_data

import com.example.recipeapp.R

data class Ingredient(
    val id:Int,
    val name:String,
    val image:Int,
    var quantity:String,
)

class Ingredients{
    var ingredientList = mutableListOf(
        Ingredient(
            id = 0,
            name = "cà chua",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 1,
            name = "tiêu",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 2,
            name = "=muối",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 3,
            name = "đường",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 4,
            name = "tiêu",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 5,
            name = "tiêu",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 6,
            name = "tiêu",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 7,
            name = "tiêu",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 8,
            name = "tiêu",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 9,
            name = "tiêu",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
        Ingredient(
            id = 10,
            name = "tiêu",
            image = R.drawable.ingredient_tomato,
            quantity = ""
        ),
    )
}