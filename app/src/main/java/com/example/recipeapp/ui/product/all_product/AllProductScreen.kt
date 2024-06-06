package com.example.recipeapp.ui.product.all_product

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.data.dynamic_data.favourite.Favourite
import com.example.recipeapp.data.static_data.Categories
import com.example.recipeapp.data.static_data.Category
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object AllProductScreenDestination: NavigationDestination {
    override val route: String = "AllProductScreen"
}

@Composable
fun LayoutAllRecipe(
    navigateToRecipeDetailScreen:(Int)->Unit,
    recipeAppViewModel: RecipeAppViewModel,
    allProductViewModel: AllProductViewModel = viewModel(factory = RecipeAppViewModel.Factory)
) {
    LaunchedEffect(Unit) {
        recipeAppViewModel.setFooterState(true)
    }
    val favouriteList by allProductViewModel.favouriteList.observeAsState(emptyList())
    val categories = remember { Categories().getCategoryList() }
    // Thêm một mục mới vào danh sách danh mục với tên "Tất cả"
    val allCategory = Category(-1, "Tất cả")
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
            selectedCategory.let { category ->
                products.filter { product ->
                    product.category.contains(category)
                }
            }
        }

        ProductList(
            favouriteList = favouriteList,
            products = filteredProducts,
            navigateToRecipeDetailScreen = navigateToRecipeDetailScreen,
            allProductViewModel = allProductViewModel
        )
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
fun ProductList(
    favouriteList:List<Favourite>,
    products: List<Product>,
    navigateToRecipeDetailScreen:(Int)->Unit,
    allProductViewModel: AllProductViewModel
) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 24.dp)
    ) {
        items(products) { product ->
            val check = allProductViewModel.fillFavourite(favouriteList,product.id)
            ProductItem(
                checkFavourite = check,
                product = product,
                navigateToRecipeDetailScreen,
                allProductViewModel
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProductItem(
    checkFavourite:Boolean,
    product: Product,
    navigateToRecipeDetailScreen:(Int)->Unit,
    allProductViewModel: AllProductViewModel
){
    Log.i("check", "$checkFavourite")
    val coroutineScope = rememberCoroutineScope()

    Box(modifier= Modifier
        .fillMaxWidth()
        .padding(start = 35.dp, end = 35.dp)
        .clickable { navigateToRecipeDetailScreen(product.id) }
    ){
        Box {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(200.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Black.copy(alpha = 0.2f))
            )
        }

        Row(
            modifier = Modifier
                .padding(end = 25.dp, top = 20.dp)
                .align(Alignment.TopEnd)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.3f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    ),
                    shape = RoundedCornerShape(8.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.time_icon),
                contentDescription = "time icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(4.dp)
            )
            Text(
                text = "${product.timeComplete} phút",
                color = Color.White,
                modifier = Modifier.width(65.dp)
            )
        }

        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                if (checkFavourite) {
                    coroutineScope.launch {
                        allProductViewModel.deleteFavourite(
                            allProductViewModel.getIdFavourite(product.id),
                            product.id
                        )
                    }

                } else {
                    coroutineScope.launch {
                        allProductViewModel.addFavourite(product.id)
                    }

                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp, bottom = 10.dp)
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = null,
                Modifier.size(30.dp),
                tint = if(checkFavourite){
                    colorResource(id = R.color.primaryColor)
                }else{
                    Color.White
                }
            )
        }
        Column(modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(start = 20.dp, bottom = 20.dp)
        ) {
            Text(text = product.name,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier.width(220.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = product.category[0].name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.7f)
            )
        }
    }
}