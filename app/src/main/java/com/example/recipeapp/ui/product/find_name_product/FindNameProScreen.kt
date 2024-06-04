package com.example.recipeapp.ui.product.find_name_product

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import com.example.recipeapp.ui.product.AllProductViewModel
import kotlinx.coroutines.launch

object FindNameProScreenDestination: NavigationDestination {
    override val route: String = "FindNameProScreen"
    const val keyproductName = "proname"
    val routeWithProductName = "${route}/{$keyproductName}"
}

@Composable
fun FindNameProScreen(
    recipeAppViewModel: RecipeAppViewModel,
    allProductViewModel: AllProductViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    navigateBack:()->Unit,
    navigateToRecipeDetailScreen:(Int)->Unit,
    findNameViewModel: FindNameViewModel = viewModel(factory = RecipeAppViewModel.Factory)
){
    val keyProductName = findNameViewModel.keyproName
    val productList = Products().productList
    val filteredProductList = productList.filter { it.name.startsWith(keyProductName, ignoreCase = true) }
    val favouriteList by allProductViewModel.favouriteList.observeAsState(emptyList())

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
                    onClick = {
                        navigateBack()
                    },
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
            Row(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    text = "Kết quả tìm kiếm: $keyProductName",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
        if (filteredProductList.isEmpty()) {
            item {
                Text(
                    text = "Không có kết quả nào được tìm thấy !!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Center
                )
            }
        } else {
            items(filteredProductList) { product ->
                val check = allProductViewModel.fillFavourite(favouriteList,product.id)
                ProductItem(
                    checkFavourite = check,
                    product = product,
                    navigateToRecipeDetailScreen,
                    allProductViewModel
                )
            }
        }
    }
}

@Composable
fun ProductItem(
    checkFavourite:Boolean,
    product: Product,
    navigateToRecipeDetailScreen:(Int)->Unit,
    allProductViewModel: AllProductViewModel = viewModel(factory = RecipeAppViewModel.Factory),
){
    val coroutineScope = rememberCoroutineScope()
    Box(modifier= Modifier
        .fillMaxWidth()
        .padding(start = 32.dp, end = 32.dp)
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
                modifier = Modifier.padding(end = 4.dp)
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
    Spacer(modifier = Modifier.padding(top = 17.dp))
}