package com.example.recipeapp.ui.product.all_product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.data.static_data.Categories
import com.example.recipeapp.data.static_data.Category
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.navigation.NavigationDestination

object AllProductScreenDestination: NavigationDestination {
    override val route: String = "AllProductScreen"
}

@Composable
fun LayoutAllRecipe(
    navigateBack:()->Unit,
    navToAllProductScreen:()->Unit,
    navigateToRecipeDetailScreen:(Int)->Unit,
) {
    val categories = remember { Categories().getCategoryList() }
    // Thêm một mục mới vào danh sách danh mục với tên "Tất cả"
    val allCategory = Category(0, "Tất cả")
    val updatedCategories = listOf(allCategory) + categories

    var selectedCategory by remember { mutableStateOf(allCategory) } // Chọn "Tất cả" mặc định
    val products = remember { Products().productList }
    var selectedCategoryIndex by remember { mutableStateOf(0) } // theo dõi chỉ mục của danh mục đã chọn

    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        Text(
            text = "Tất cả công thức",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 15.dp)
        )

        CategoryList(
            categories = updatedCategories,
            onCategorySelected = { category ->
                selectedCategory = category
                selectedCategoryIndex = updatedCategories.indexOf(category)
            },
            selectedCategoryIndex = selectedCategoryIndex
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Kiểm tra xem danh mục đã chọn có phải là "Tất cả" hay không
        val filteredProducts = if (selectedCategory == allCategory) {
            products // Hiển thị tất cả
        } else {
            selectedCategory?.let { category ->
                products.filter { product ->
                    product.category.contains(category)
                }
            } ?: products
        }

        ProductList(products = filteredProducts, navigateToRecipeDetailScreen)
    }
}

@Composable
fun CategoryList(
    categories: List<Category>,
    onCategorySelected: (Category) -> Unit,
    selectedCategoryIndex: Int
) {
    LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
        itemsIndexed(categories) { index, category ->
            CategoryItem(
                category = category,
                onCategorySelected = onCategorySelected,
                isSelected = index == selectedCategoryIndex
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onCategorySelected: (Category) -> Unit,
    isSelected: Boolean = false
) {
    Button(
        onClick = { onCategorySelected(category) },
        modifier = Modifier.padding(horizontal = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.Magenta else Color.Transparent,
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(text = category.name)
    }
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
