package com.example.recipeapp.ui.recipe

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object IngredientDestination: NavigationDestination {
    override val route: String = "ingredient"
    const val productId = "itemId"
    val routeWithId = "$route/{$productId}"
}

@Composable
fun LayoutIngredient(
    recipeAppViewModel: RecipeAppViewModel,
    viewModel: RecipeViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    navToBack:()-> Unit,
){
    LaunchedEffect(Unit) {
        recipeAppViewModel.setFooterState(false)
    }

    val product = Products().productList[viewModel.productId]
    val checkAddShopping = viewModel.uiState.checkAddShopping
    var checkPages by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = { Topbar(
            checkAddShopping = checkAddShopping,
            viewModel = viewModel,
            navToBack = navToBack,
        ) },
        containerColor = Color.White
    ) { padding->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            ImageCard(
                product = product,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row {
                Button(onClick = {
                    checkPages = true
                },
                    shape = RoundedCornerShape(8.dp),
                    colors = if(checkPages){
                        ButtonDefaults.buttonColors(Color(0,150,136))
                    }else{
                        ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0,150,136)
                        )
                    },
                    modifier = Modifier.width(180.dp)
                ) {
                    Text(text = "Nguyên liệu")
                }
                Button(onClick = {
                    checkPages = false
                },
                    shape = RoundedCornerShape(8.dp),
                    colors = if(!checkPages){
                        ButtonDefaults.buttonColors(Color(0,150,136))
                    }else{
                        ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0,150,136)
                        )
                    },
                    modifier = Modifier.width(180.dp)
                ) {
                    Text(text = "Cách làm")
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))

            if(checkPages){
                LazyColumn{
                    items(
                        items = product.ingredient
                    ){
                        Ingredient(
                            img = it.image,
                            name = it.name,
                            kg = it.quantity
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }else {
                LazyColumn {
                    items(
                        items = product.procedure
                    ) {
                        Box(
                            Modifier
                                .background(Color.LightGray, RoundedCornerShape(8.dp))
                                .fillMaxWidth()
                        ) {
                            Produce(step = it.step, des = it.des)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(
    checkAddShopping: Boolean,
    viewModel:RecipeViewModel,
    navToBack: () -> Unit,
){
    Column(modifier = Modifier.padding(end = 16.dp)) {
        CenterAlignedTopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { navToBack() }) {
                    Image(imageVector = Icons.Default.ArrowBack,
                        contentDescription = null)
                }
            },
            actions = {
                IconButton(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .let {
                            if (checkAddShopping) {
                                it.background(colorResource(id = R.color.primaryColor))
                            } else {
                                it.background(Color.LightGray)
                            }
                        },
                    enabled = checkAddShopping,
                    onClick = { viewModel.addShopping() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "add shopping",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.White)
        )

    }

}

@Composable
fun ImageCard(
    product: Product,
    viewModel: RecipeViewModel
){
    val coroutineScope = rememberCoroutineScope()
    val checkActionFavourite = viewModel.uiState.checkFavorite

    Box(modifier= Modifier.fillMaxWidth()){

        Box{
            Image(
                painter = painterResource(id = product.image),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(249.dp)
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

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                modifier = Modifier.width(220.dp)
            )

            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    Log.i("checkBegin", "$checkActionFavourite")
                    if (checkActionFavourite > 0) {
                        coroutineScope.launch {
                            viewModel.deleteFavourite()
                            viewModel.updateCheckFavourite()
                            Log.i("checkdelete", "$checkActionFavourite")
                        }
                    } else {
                        coroutineScope.launch {
                            viewModel.addFavourite()
                            viewModel.updateCheckFavourite()
                            Log.i("checkadd", "$checkActionFavourite")
                        }

                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
                modifier = Modifier
                    .padding(end = 10.dp, bottom = 10.dp)
            ) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null,
                    Modifier.size(30.dp),
                    tint = if(checkActionFavourite > 0){
                        colorResource(id = R.color.primaryColor)
                    }else{
                        Color.White
                    }
                )
            }
        }
    }
}

@Composable
fun Ingredient(img:Int,name:String,kg:String){
    Box(
        Modifier
            .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = img), contentDescription =null,
                Modifier
                    .size(70.dp)
                    .padding(16.dp)
                    .weight(1f))
            Text(text = name,Modifier.weight(1f),
                fontWeight = FontWeight(weight = 1000),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Text(text = kg,
                Modifier
                    .alpha(0.3f)
                    .weight(1f),
                fontWeight = FontWeight(weight = 1000),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Produce(step:String,des:String){
    Box(
        Modifier
            .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = step,
                Modifier.padding(20.dp),
                fontWeight = FontWeight(weight = 1000),
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = des,
                Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .alpha(0.3f),
                fontWeight = FontWeight(weight = 700),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}