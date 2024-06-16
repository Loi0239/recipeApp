package com.example.recipeapp.ui.schedule.findingForSchedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import com.example.recipeapp.ui.recipe.RecipeViewModel

object FindForScheduleDestination:NavigationDestination{
    override val route: String = "findForSchedule"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindForScheduleScreen(
    navToBack:()->Unit,
    viewModel: FindForScheduleViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    recipeAppViewModel:RecipeAppViewModel,
){
    var searchWord by remember {
        mutableStateOf("")
    }

    val productList = viewModel.getListProduct(searchWord)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Kế hoạch bữa ăn",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navToBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = searchWord,
                onValueChange = {searchWord = it},
                placeholder = { Text(text = "Tìm kiếm công thức")},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                trailingIcon = {
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(
                           imageVector = Icons.Default.Search,
                           contentDescription = "search recipe")
                   }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.primaryColor),
                    unfocusedBorderColor = Color.Gray
                ),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.padding(horizontal = 32.dp)
            ){
                items(
                    items = productList
                ){ product ->
                    ProductItem(
                        product = product,
                        navToBack = navToBack,
                        recipeAppViewModel = recipeAppViewModel,
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    navToBack:()->Unit,
    recipeAppViewModel: RecipeAppViewModel
) {
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .clickable {
                recipeAppViewModel.updateIdProductSchedule(product.id)
                navToBack()
            }
            .background(
                color = colorResource(id = R.color.primaryColor).copy(alpha = 0.4f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painter = painterResource(id = product.image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier= Modifier
                .size(width = 100.dp, height = 80.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Column(
            modifier = Modifier
                .padding(start = 20.dp, bottom = 20.dp),
        ) {
            Text(text = product.name,
                fontWeight = FontWeight(weight = 1000),
                style = MaterialTheme.typography.titleMedium,
                )
            Text(text = product.category[0].name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .alpha(0.3f)
            )
        }
    }
    Spacer(modifier = Modifier.padding(top = 20.dp))
}

@Preview(showSystemUi = true)
@Composable
fun PreviewFindForSchedule(){
    FindForScheduleScreen(
        navToBack = {},
        recipeAppViewModel = viewModel(factory = RecipeAppViewModel.Factory)
    )
}