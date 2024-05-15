package com.example.recipeapp.data.static_data

import androidx.compose.animation.SizeTransform
import com.example.recipeapp.R

data class Product(
    val id:Int,
    val name:String,
    val image:Int,
    val timeComplete:Int,
    val ingredient: List<Ingredient>,
    val procedure: List<Procedure>,
    val category:List<Category>,
)

data class Procedure(
    val step:String,
    val des:String,
)

class Products{
    val productList = mutableListOf(
        Product(
            id = 0,
            name = "phở",
            image = R.drawable.food_example,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[0].apply { quantity = "20 g" },
                Ingredients().ingredientList[1].apply { quantity = "3 g" },
                Ingredients().ingredientList[2].apply { quantity = "5 g" }
            ),
            procedure = listOf(
                Procedure(
                    step = "bước 1",
                    des = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?1"
                ),
                Procedure(
                    step = "bước 2",
                    des = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?2"
                ),
                Procedure(
                    step = "bước 3",
                    des = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?3"
                ),
            ),
            category = listOf(
                Categories().listCategory[0]
            )
        ),
        Product(
            id = 1,
            name = "cơm",
            image = R.drawable.food_example,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[0].apply { quantity = "20 g" },
                Ingredients().ingredientList[1].apply { quantity = "3 g" },
                Ingredients().ingredientList[3].apply { quantity = "5 g" }

            ),
            procedure = listOf(
                Procedure(
                    step = "bước 1",
                    des = ""
                )
            ),
            category = listOf(
                Categories().listCategory[0]
            )
        ),
        Product(
            id = 2,
            name = "bún",
            image = R.drawable.food_example,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[1].apply { quantity = "20 g" },
                Ingredients().ingredientList[3].apply { quantity = "20 g" }

            ),
            procedure = listOf(
                Procedure(
                    step = "bước 1",
                    des = ""
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 3,
            name = "Phở",
            image = R.drawable.food_example,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[0].apply { quantity = "20 g" }
            ),
            procedure = listOf(
                Procedure(
                    step = "bước 1",
                    des = ""
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 4,
            name = "Phở",
            image = R.drawable.food_example,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[0].apply { quantity = "20 g" }
            ),
            procedure = listOf(
                Procedure(
                    step = "bước 1",
                    des = ""
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 5,
            name = "Phở",
            image = R.drawable.food_example,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[0].apply { quantity = "20 g" }
            ),
            procedure = listOf(
                Procedure(
                    step = "bước 1",
                    des = ""
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
        Product(
            id = 6,
            name = "Phở",
            image = R.drawable.food_example,
            timeComplete = 20,
            ingredient = listOf(
                Ingredients().ingredientList[0].apply { quantity = "20 g" }
            ),
            procedure = listOf(
                Procedure(
                    step = "bước 1",
                    des = ""
                )
            ),
            category = listOf(
                Categories().listCategory[1]
            )
        ),
    )

    fun getProduct(idProduct: Int): Product{
        return productList[idProduct]
    }

    fun getIngredient(idProduct:Int):List<Ingredient>{
        return productList[idProduct].ingredient
    }
}