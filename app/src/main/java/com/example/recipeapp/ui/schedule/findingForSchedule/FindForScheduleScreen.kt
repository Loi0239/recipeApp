package com.example.recipeapp.ui.schedule.findingForSchedule

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    recipeAppViewModel:RecipeAppViewModel,
){
    val list = Products().productList

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "lịch trình & kế hoạch bữa ăn",
                        style = MaterialTheme.typography.headlineLarge
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
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "tìm kiếm công thức")},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
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
                    items = list
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
    Box(modifier= Modifier
        .fillMaxWidth()
        .clickable {
            recipeAppViewModel.updateIdProductSchedule(product.id)
            navToBack()
        }
    ){
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

//@Preview(showSystemUi = true)
//@Composable
//fun PreviewFindForSchedule(){
//    FindForScheduleScreen(
//        navToBack = {},
//    )
//}