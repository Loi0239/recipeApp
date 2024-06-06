package com.example.recipeapp.ui.favouriteRecipe

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
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.recipeapp.data.static_data.Category
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object FavouriteDestination:NavigationDestination{
    override val route: String = "favourite"
}

@Composable
fun RecipeFavoriteScreen(
    recipeAppViewModel:RecipeAppViewModel,
    favouriteRecipeViewModel: FavouriteRecipeViewModel =
        viewModel(factory = RecipeAppViewModel.Factory),
    navigateToRecipeDetailScreen:(Int)->Unit,
){
    LaunchedEffect(Unit) {
        recipeAppViewModel.setFooterState(true)
    }

    val listFavourite by favouriteRecipeViewModel.favouriteList.observeAsState(emptyList())
    val categoryList = favouriteRecipeViewModel.updatedCategories

    var selectedCategory by remember { mutableStateOf(favouriteRecipeViewModel.allCategory) } // Chọn "Tất cả" mặc định
    var selectedCategoryIndex by remember { mutableIntStateOf(0) } // theo dõi chỉ mục của danh mục đã chọn

    Column(
        modifier = Modifier.padding(bottom = 60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 15.dp)
        ) {
            Text(
                text = "Danh sách yêu thích",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
        CategoryList(
            categories = categoryList,
            onCategorySelected = { category ->
                selectedCategory = category
                selectedCategoryIndex = categoryList.indexOf(category)
            },
            selectedCategoryIndex = selectedCategoryIndex
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))

        // Kiểm tra xem danh mục đã chọn có phải là "Tất cả" hay không
        val filteredProducts = if (selectedCategory == favouriteRecipeViewModel.allCategory) {
            listFavourite // Hiển thị tất cả
        } else {
            selectedCategory.let { category ->
                listFavourite.filter { favourite ->
                    val product = favouriteRecipeViewModel.getProduct(favourite.idProduct)
                    product.category.contains(category)
                }
            }
        }

        ProductList(
            favouriteList = filteredProducts,
            navigateToRecipeDetailScreen = navigateToRecipeDetailScreen,
            favouriteRecipeViewModel = favouriteRecipeViewModel
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
            containerColor = if (isSelected) Color(0xff129575) else Color.Transparent,
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(text = category.name)
    }
}

@Composable
fun ProductList(
    favouriteList: List<Favourite>,
    navigateToRecipeDetailScreen:(Int)->Unit,
    favouriteRecipeViewModel: FavouriteRecipeViewModel
) {
    LazyColumn {
        items(favouriteList) { favourite ->
            CardRecipe(
                favourite = favourite,
                navigateToRecipeDetailScreen = navigateToRecipeDetailScreen,
                favouriteRecipeViewModel = favouriteRecipeViewModel
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CardRecipe(
    favourite: Favourite,
    navigateToRecipeDetailScreen: (Int) -> Unit,
    favouriteRecipeViewModel: FavouriteRecipeViewModel
){
    val coroutineScope = rememberCoroutineScope()
    val product = favouriteRecipeViewModel.getProduct(favourite.idProduct)

    Box(modifier= Modifier
        .fillMaxWidth()
        .padding(start = 35.dp, end = 35.dp)
        .clickable { navigateToRecipeDetailScreen(favourite.idProduct) }
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
                modifier = Modifier.padding(end = 4.dp)
            )
        }

        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                coroutineScope.launch {
                    favouriteRecipeViewModel.deleteFavourite(favourite.id, favourite.idProduct)
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
                tint = colorResource(id = R.color.primaryColor)
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