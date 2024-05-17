package com.example.recipeapp.ui.product.find_name_product

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination

object FindNameProScreenDestination: NavigationDestination {
    override val route: String = "FindNameProScreen"
    const val keyproductName = "proname"
    val routeWithProductName = "${route}/{$keyproductName}"
}

@Composable
fun FindNameProScreen(
    recipeAppViewModel: RecipeAppViewModel,
    navigateBack:()->Unit,
    navigateToRecipeDetailScreen:(Int)->Unit,
    findNameViewModel: FindNameViewModel = viewModel(factory = RecipeAppViewModel.Factory)
){
    val keyProductName = findNameViewModel.keyproName
    val productList = Products().productList
    val filteredProductList = productList.filter { it.name.contains(keyProductName, ignoreCase = true) }

    LaunchedEffect(Unit) {
        recipeAppViewModel.setFooterState(false)
    }

    LazyColumn {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { navigateBack() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )

                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier.size(30.dp),
                        Color(0xff74777a)
                    )
                }
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "Tìm kiếm công thức",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
        item { 
            Row(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    text = "Kết quả tìm kiếm: $keyProductName",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
        items(filteredProductList) { product ->
            ProductItem(product = product, navigateToRecipeDetailScreen)
        }
    }
}
@Composable
fun ProductItem(
    product: Product,
    navigateToRecipeDetailScreen:(Int)->Unit,
) {
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
            Text(text = product.category[0].name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.3f))
        }
    }
    Spacer(modifier = Modifier.padding(top = 20.dp))
}