package com.example.recipeapp.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.data.static_data.Categories
import com.example.recipeapp.data.static_data.Category
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
object HomeDestination: NavigationDestination {
    override val route: String = "HomeScreen"
}
@Composable
fun HomeScreen(
    recipeAppViewModel: RecipeAppViewModel,
    navigateToAllProductScreen:()->Unit,
    navigateToCategoryProductScreen:(Int)->Unit,
    navigateToRecipeDetailScreen:(Int)->Unit,
    navigateToFindNameProScreen:(String)->Unit,
) {
    LaunchedEffect(Unit) {
        recipeAppViewModel.setFooterState(true)
    }

    val categories = remember { Categories().getCategoryList() }
    val products = remember { Products().productList }
    val textFieldValue = remember { mutableStateOf(TextFieldValue()) }
    val filteredProducts = remember { mutableStateOf(emptyList<Product>()) }
    val isSearchPerformed = remember { mutableStateOf(false) }

    Box {
        LazyColumn(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 70.dp)
                .fillMaxWidth()
        ) {
            item {
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Text(text = "Xin chào,", style = MaterialTheme.typography.displayLarge)
                Text(text = "Người dùng.", style = MaterialTheme.typography.displayLarge)
                Text(
                    text = "Hãy chọn công thức phù hợp cho bữa ăn của gia đình bạn nào !!",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Normal
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = textFieldValue.value,
                    onValueChange = {
                        textFieldValue.value = it
                        filteredProducts.value = if (it.text.isNotEmpty()) {
                            products.filter { product ->
                                product.name.contains(it.text, ignoreCase = true)
                            }
                        } else {
                            emptyList()
                        }
                    },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    placeholder = { Text(text = "Tìm kiếm") },
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFF0F0F0), RoundedCornerShape(16.dp))
                        .clickable { navigateToFindNameProScreen(textFieldValue.value.text) },
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        isSearchPerformed.value = true
                    })
                )

                if (textFieldValue.value.text.isNotEmpty() && isSearchPerformed.value) {
                    navigateToFindNameProScreen(textFieldValue.value.text)
                }
                Row(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                ) {
                    if (textFieldValue.value.text.isNotEmpty()) {
                        ProductListFind(products = filteredProducts.value, navigateToRecipeDetailScreen)
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Danh mục",
                        style = MaterialTheme.typography.displaySmall,
                        fontSize = 22.sp
                    )
                    Text(
                        text = "Xem tất cả",
                        style = MaterialTheme.typography.headlineLarge,
                        color = colorResource(id = R.color.primaryColor),
                        modifier = Modifier
                            .padding(top = 7.dp)
                            .clickable { navigateToAllProductScreen() }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.padding(top = 5.dp))
                CategoryList(categories = categories, navigateToCategoryProductScreen)
            }
            item {
                Spacer(modifier = Modifier.padding(top = 28.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Công thức hôm nay",
                        style = MaterialTheme.typography.displaySmall,
                        fontSize = 22.sp
                    )
                    Text(
                        text = "Xem tất cả",
                        style = MaterialTheme.typography.headlineLarge,
                        color = colorResource(id = R.color.primaryColor),
                        modifier = Modifier
                            .padding(top = 7.dp)
                            .clickable { navigateToAllProductScreen() }
                    )
                }
            }
            item {
                val productsRepository = Products()
                val randomProducts = productsRepository.getRandomProducts(5)
                Spacer(modifier = Modifier.padding(top = 20.dp))
                LazyRow{
                    item {
                        ProductListRanDom(
                            products = randomProducts,
                            navigateToRecipeDetailScreen = navigateToRecipeDetailScreen
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Công thức mới nhất",
                        style = MaterialTheme.typography.displaySmall,
                        fontSize = 22.sp
                    )
                    Text(
                        text = "Xem tất cả",
                        style = MaterialTheme.typography.headlineLarge,
                        color = colorResource(id = R.color.primaryColor),
                        modifier = Modifier
                            .padding(top = 7.dp)
                            .clickable { navigateToAllProductScreen() }
                    )
                }
            }
            item {
                val productsRepository = Products()
                val lastFiveProducts = productsRepository.getLastFiveProducts()
                Spacer(modifier = Modifier.padding(top = 20.dp))
                ProductListNew(
                    products = lastFiveProducts,
                    navigateToRecipeDetailScreen = navigateToRecipeDetailScreen
                )
            }
        }
    }
}

// Hiển thị danh mục
@Composable
fun CategoryList(
    categories: List<Category>,
    navigateToCategoryProductScreen:(Int)->Unit,
) {
    LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
        items(categories) {category ->
            CategoryItem(category = category, navigateToCategoryProductScreen)
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    navigateToCategoryProductScreen:(Int)->Unit,
) {
    Button(
        onClick = { navigateToCategoryProductScreen(category.id) },
        modifier = Modifier.padding(horizontal = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(text = category.name)
    }
}

// Hiển thị danh sách gợi ý tìm kiếm
@Composable
fun ProductListFind(
    products: List<Product>,
    navigateToRecipeDetailScreen: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        products.forEach { product ->
            ProductItemFind(product = product, navigateToRecipeDetailScreen)
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
    }
}
@Composable
fun ProductItemFind(product: Product, navigateToRecipeDetailScreen:(Int)->Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { navigateToRecipeDetailScreen(product.id) }
            .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = product.name,
            color = Color.Black
        )
    }
}

// Hiển thị danh sách random
@Composable
fun ProductListRanDom(
    products: List<Product>,
    navigateToRecipeDetailScreen: (Int) -> Unit
) {
    products.forEach { product ->
        ProductItemRanDom(product = product, navigateToRecipeDetailScreen)
    }
}
@Composable
fun ProductItemRanDom(
    product: Product,
    navigateToRecipeDetailScreen:(Int)->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToRecipeDetailScreen(product.id) }
            .padding(end = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painterResource(
                id = product.image
            ),
            contentDescription = null,
            modifier = Modifier
                .width(240.dp)
                .height(160.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Color(0xffF5F5DC))
                .padding(8.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(start = 6.dp)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row {
                Text(
                    text = "\uD83D\uDD50 ${product.timeComplete} phút",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = 6.dp, end = 70.dp)

                )
                Box(
                    modifier = Modifier
                        .background(colorResource(id = R.color.primaryColor))
                        .padding(3.dp),
                ) {
                    Text(
                        text = product.category[0].name,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}
// Hiển thị danh sách sản phẩm mới nhất
@Composable
fun ProductListNew(
    products: List<Product>,
    navigateToRecipeDetailScreen: (Int) -> Unit
) {
    products.forEach { product ->
        ProductItemNew(product = product, navigateToRecipeDetailScreen)
    }
}
@Composable
fun ProductItemNew(
    product: Product,
    navigateToRecipeDetailScreen:(Int)->Unit
) {
    Row(
        modifier = Modifier
            .clickable { navigateToRecipeDetailScreen(product.id) }
            .background(Color(0xffF5F5DC)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = "image",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .size(78.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "\uD83D\uDD50 ${product.timeComplete} phút",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { navigateToRecipeDetailScreen(product.id) },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primaryColor)),
                    modifier = Modifier.size(100.dp, 35.dp),
                    contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = "Xem ngay",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.padding(top = 20.dp))
}