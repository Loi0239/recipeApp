package com.example.recipeapp.ui.product.category_product

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import com.example.recipeapp.ui.recipe.RecipeViewModel

object CategoryProductScreenDestination: NavigationDestination {
    override val route: String = "Category Product Screen"
    const val categoryId = "cateId"
    val routeWithId1 = "$route/{$categoryId}"
}
@Composable
fun CategoryProductScreen(
    recipeViewModel: CateProViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    navigateBack:()->Unit,
    navToCategoryProduct:()->Unit,
    navigateToRecipeDetailScreen:(Int)->Unit,
){
    val cateId = recipeViewModel.cateId
    Text(text = "$cateId")
    val products: List<Product> = Products().getProductsByCategoryId(cateId)
    val productListString = buildString {
        for (product in products) {
            append("${product.name}, ")
        }
    }

    Text(text = productListString)
    ProductList(products = products, navigateToRecipeDetailScreen)

}

@Composable
fun ProductList(products: List<Product>, navigateToRecipeDetailScreen:(Int)->Unit,) {
    LazyColumn {
        items(products) { product ->
            ProductItem(product = product, navigateToRecipeDetailScreen)
        }
    }
}

@Composable
fun ProductItem(product: Product, navigateToRecipeDetailScreen:(Int)->Unit,){
    Box(modifier= Modifier
        .fillMaxWidth()
        .padding(start = 35.dp, end = 35.dp)
        .clickable { navigateToRecipeDetailScreen(product.id) }){
        Image(painter = painterResource(id = product.image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier= Modifier
                .size(width = 1000.dp, height = 200.dp)
                .clip(RoundedCornerShape(20.dp)))

        Column(modifier = Modifier
            .padding(end = 25.dp, top = 20.dp)
            .align(Alignment.TopEnd) ){
            Button(modifier = Modifier.size(70.dp,32.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(248, 235, 114, 255)),
                contentPadding = ButtonDefaults.TextButtonWithIconContentPadding,
            ) {
                Icon(Icons.Default.Star, contentDescription = null)
                Text(text = "4.0", color = Color.Black, modifier = Modifier.size(width = 30.dp, height = 20.dp))
            }
        }
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp, bottom = 10.dp)
        ) {
            Icon(Icons.Default.FavoriteBorder, contentDescription = null, Modifier.size(30.dp))
        }
        Column(modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(start = 20.dp, bottom = 20.dp)) {
            Text(text = product.name,
                fontWeight = FontWeight(weight = 1000),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.size(height = 50.dp, width = 220.dp))
            Text(text = "(by Alex)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.3f))
        }
    }
}