package com.example.recipeapp.ui.recipe_person

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePerson
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object ShowRecipeDestination: NavigationDestination {
    override val route: String = "Show recipe"
}
@Composable
fun RecipePerson(
    viewModel: ShowRecipeViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    navigateBack:()->Unit,
    navToShowRecipe:()->Unit,
    navigateToUpdateRecipe:(Int)->Unit,
){
    val coroutineScope = rememberCoroutineScope()
    val homeUiState by viewModel.showDataState.collectAsState()

    LazyColumn{
        item {
            Column {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Trang cá nhân",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(shape = CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "John Doe",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 35.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        Text(
                            text = "Công thức",
                            fontSize = 16.sp,
                            color = Color(0xFFA9A9A9)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "4",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        Text(
                            text = "Yêu thích",
                            fontSize = 16.sp,
                            color = Color(0xFFA9A9A9)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "14",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Column {
                        Text(
                            text = "Nội trợ",
                            fontSize = 16.sp,
                            color = Color(0xffA9A9A9),
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Passionate about food and life \uD83E\uDD58\uD83C\uDF72\uD83C\uDF5D\uD83C\uDF71",
                            fontSize = 16.sp,
                            color = Color(0xffA9A9A9),
                            textAlign = TextAlign.Left
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 15.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 5.dp, end = 5.dp),color = Color.Gray
                )
            }
        }
        item {
            Spacer(modifier = Modifier.padding(top = 25.dp))
            Row(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = "Danh sách công thức của bạn",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            RecipeList(
                itemList = homeUiState.itemList,
                onDeleteClicked = { recipePerson ->
                    coroutineScope.launch {
                        viewModel.deleteRecipe(recipePerson)
                    }
                },
                navigateToUpdateRecipe
            )
            Spacer(modifier = Modifier.padding(bottom = 35.dp))
        }
    }
}

@Composable
private fun RecipeList(
    itemList: List<RecipePerson>,
    onDeleteClicked: (RecipePerson) -> Unit,
    navigateToUpdateRecipe:(Int)->Unit,
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    ) {
        itemList.forEach { item ->
            RecipeItem(
                item = item,
                onDeleteClicked = onDeleteClicked,
                navigateToUpdateRecipe = navigateToUpdateRecipe
            )
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
        }
    }
}

@Composable
private fun RecipeItem(
    item: RecipePerson,
    onDeleteClicked: (RecipePerson) -> Unit,
    navigateToUpdateRecipe:(Int) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .weight(2f)
            ) {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(
                    text = item.nameRecipe,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row {
                    Text(
                        text = "item.ingredient",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                Spacer(modifier = Modifier.padding(top = 5.dp))
                Text(text = "\uD83D\uDD50 ${item.time} phút")
                Spacer(modifier = Modifier.padding(top = 10.dp))
            }

            Column {
                Button(
                    onClick = { navigateToUpdateRecipe(item.id) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(Icons.Default.Create, contentDescription = "Fix")
                }
                Button(
                    onClick = { onDeleteClicked(item) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Del")
                }
            }
        }
    }
}