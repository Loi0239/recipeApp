package com.example.recipeapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.data.static_data.Categories
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import com.example.recipeapp.ui.theme.RecipeAppTheme

object HomeDestination: NavigationDestination {
    override val route: String = "home"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    recipeAppViewModel: RecipeAppViewModel,
    viewModel: HomeViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    navToHome:()->Unit,
) {
    LaunchedEffect(Unit) {
        recipeAppViewModel.setFooterState(true)
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    placeholder = { Text(text = "Search") },
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .background(color = Color(0xffE1E1E1)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.primaryColor),
                        unfocusedBorderColor = Color.Unspecified
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(36.dp))
            val list = Categories().listCategory
            LazyRow() {
                items(
                    items = list
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(75.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = colorResource(id = R.color.primaryColor)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.iconfacebook),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Dinner",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
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
                    modifier = Modifier.padding(top = 7.dp)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.padding(top = 25.dp))
            val list2 = Products().productList
            LazyRow {
                items(
                    items = list2
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Image(
                            painterResource(
                                id = R.drawable.iconfacebook
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .width(212.dp)
                                .height(160.dp),
                            contentScale = ContentScale.FillWidth
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Vegan Chickpea Curry",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.padding(start = 6.dp)
                            )
                            Text(
                                text = "Loaded with protein",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.padding(start = 6.dp)
                            )
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            Row {
                                Text(
                                    text = "Rs 150",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(start = 6.dp, end = 100.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .clip(shape = RoundedCornerShape(4.dp))
                                        .background(color = colorResource(id = R.color.primaryColor))
                                ) {
                                    Text(
                                        text = "5 left",
                                        color = Color.White,
                                        style = MaterialTheme.typography.titleSmall,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.width(50.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(top = 28.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Popular dish",
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = "See all",
                    style = MaterialTheme.typography.headlineLarge,
                    color = colorResource(id = R.color.primaryColor)
                )
            }
        }
//        val list3 = StatusDetailRepository.statusDetails
//        items(
//            items = list3
//        ) {
//            Spacer(modifier = Modifier.padding(top = 14.dp))
//            CardDish(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .shadow(elevation = 1.dp),
//                nameProduct = it.mainText,
//                imageProduct = it.image,
//                description = it.subText
//            )
//        }
    }
}

@Composable
fun CardDish(
    modifier: Modifier = Modifier,
    nameProduct: Int,
    imageProduct: Int,
    description: Int,
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageProduct),
            contentDescription = "image shop",
            modifier = Modifier
                .padding(start = 8.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .size(78.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = nameProduct),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = description),
                style = MaterialTheme.typography.titleSmall
            )
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "150.000 VNĐ",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primaryColor)),
                    modifier = Modifier.size(100.dp, 35.dp),
                    contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = "Mua ngay",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier){
    val viewModel: RecipeAppViewModel = viewModel()
    Box(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val footerItems = viewModel.footerItems.observeAsState(initial = emptyList()).value
            footerItems?.forEach { footerItem ->
                Icon(
                    footerItem.nameIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { viewModel.onItemClicked(footerItem.id) }
                        .let {
                            if (footerItem.checked) {
                                it
                                    .background(color = Color(0xffECECEC))
                                    .padding(8.dp)
                            } else {
                                it
                                    .background(color = Color.Unspecified)
                                    .padding(8.dp)
                            }
                        },
                    tint = if (footerItem.checked) {
                        colorResource(id = R.color.primaryColor)
                    } else {
                        Color.Unspecified
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    RecipeAppTheme {
        val backgroundColor = Color(0xfff6f6f7)
        Column(modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)) {
            Box(modifier = Modifier.fillMaxSize()) {
                HomeScreen(
                    navToHome = {},
                    recipeAppViewModel = viewModel(),
                )
                Footer(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter))
            }
        }
    }
}