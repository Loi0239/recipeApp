package com.example.recipeapp.ui.home

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    navigateToAddRecipe:()->Unit,
    navigateToShowRecipe:()->Unit,
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
        Row {
            Button(onClick = { navigateToAddRecipe() }) {
                Text(text = "Add")
            }
            Button(onClick = { navigateToShowRecipe() }) {
                Text(text = "Show")
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 80.dp)
                .fillMaxWidth()
        ) {
            item {
                Text(text = "Hello,", style = MaterialTheme.typography.displayLarge)
                Text(text = "Abhishek.", style = MaterialTheme.typography.displayLarge)
                Text(
                    text = "What do you want to eat?",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Normal
                )
            }
            item {
                Spacer(modifier = Modifier.height(36.dp))
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
                    placeholder = { Text(text = "Search") },
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                        .fillMaxWidth()
                        .background(Color(0xffE1E1E1), RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { navigateToFindNameProScreen(textFieldValue.value.text) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search // Thiết lập hành động IME thành Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        isSearchPerformed.value = true // Thiết lập trạng thái tìm kiếm khi người dùng nhấn nút tìm kiếm trên bàn phím
                    })
                )

                if (textFieldValue.value.text.isNotEmpty() && isSearchPerformed.value) {
                    navigateToFindNameProScreen(textFieldValue.value.text)
                }
                if (textFieldValue.value.text.isNotEmpty()) {
                    ProductList(products = filteredProducts.value, navigateToRecipeDetailScreen)
                }
            }
            item {
                Spacer(modifier = Modifier.height(36.dp))
                CategoryList(categories = categories, navigateToCategoryProductScreen)
            }
            item {
                Spacer(modifier = Modifier.padding(top = 28.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Today’s special",
                        style = MaterialTheme.typography.displaySmall,
                        fontSize = 27.sp
                    )
                    Text(
                        text = "See all",
                        style = MaterialTheme.typography.headlineLarge,
                        color = colorResource(id = R.color.primaryColor),
                        modifier = Modifier
                            .padding(top = 7.dp)
                            .clickable { navigateToAllProductScreen() }
                    )
                }
            }
            item {
                Demo_ExposedDropdownMenuBox()
            }
        }
    }
}

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

@Composable
fun ProductList(
    products: List<Product>,
    navigateToRecipeDetailScreen: (Int) -> Unit
) {
    Column {
        products.forEach { product ->
            ProductItem(product = product, navigateToRecipeDetailScreen)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun ProductItem(product: Product, navigateToRecipeDetailScreen:(Int)->Unit,) {
    Text(text = product.name, modifier = Modifier.clickable { navigateToRecipeDetailScreen(product.id) })
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                coffeeDrinks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}