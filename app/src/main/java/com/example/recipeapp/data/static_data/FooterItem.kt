package com.example.recipeapp.data.static_data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class FooterItem(
    val id: Int,
    val nameIcon: ImageVector,
    val checked:Boolean
)

object FooterRepository{
    var footerItems = listOf(
        FooterItem(
            id = 1,
            nameIcon = Icons.Default.Home,
            checked = true
        ),
        FooterItem(
            id = 2,
            nameIcon = Icons.Default.Favorite,
            checked = false
        ),
        FooterItem(
            id = 3,
            nameIcon = Icons.Default.Person,
            checked = false
        ),
        FooterItem(
            id = 4,
            nameIcon = Icons.Default.ShoppingCart,
            checked = false
        ),
        FooterItem(
            id = 5,
            nameIcon = Icons.Default.Search,
            checked = false
        )
    )
}
