package com.example.recipeapp.ui.recipe_person

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
    recipeAppViewModel: RecipeAppViewModel,
    viewModel: ShowRecipeViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    navigateToUpdateRecipe:(Int)->Unit,
    navigateToRecipeDetailPerson:(Int)->Unit
){
    val coroutineScope = rememberCoroutineScope()
    val homeUiState by viewModel.showDataState.collectAsState()
    val countRecipe by viewModel.countRecipeState.collectAsState()
    val countFavour by viewModel.countFavourState.collectAsState()

    LaunchedEffect(Unit) {
        recipeAppViewModel.setFooterState(true)
    }

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
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(shape = CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Người dùng",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 25.dp))
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
                            text = "$countRecipe",
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
                            text = "$countFavour",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
//                Row(
//                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
//                ) {
//                    Spacer(modifier = Modifier.height(5.dp))
//                    Column {
//                        Text(
//                            text = "Nội trợ",
//                            fontSize = 16.sp,
//                            color = Color(0xffA9A9A9),
//                            textAlign = TextAlign.Left
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "Passionate about food and life \uD83E\uDD58\uD83C\uDF72\uD83C\uDF5D\uD83C\uDF71",
//                            fontSize = 16.sp,
//                            color = Color(0xffA9A9A9),
//                            textAlign = TextAlign.Left
//                        )
//                    }
//                }
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
                    text = "Danh sách công thức của tôi",
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
                navigateToUpdateRecipe,
                navigateToRecipeDetailPerson
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
    navigateToRecipeDetailPerson:(Int) -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    ) {
        itemList.forEach { item ->
            RecipeItem(
                item = item,
                onDeleteClicked = onDeleteClicked,
                navigateToUpdateRecipe = navigateToUpdateRecipe,
                navigateToRecipeDetailPerson
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
    navigateToRecipeDetailPerson:(Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToRecipeDetailPerson(item.id) }
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
                        text = item.step,
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
                DialogButtonDeleteRecipe(item = item, onDeleteClicked = onDeleteClicked)
            }
        }
    }
}

@Composable
fun DialogButtonDeleteRecipe(
    item: RecipePerson,
    onDeleteClicked: (RecipePerson) -> Unit,
) {
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Button(
        onClick = { openDialog.value = true },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        )
    ) {
        Icon(Icons.Default.Delete, contentDescription = "Del")
    }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    onDeleteClicked(item)
                    openDialog.value = false
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Xác nhận")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text("Hủy")
                }
            },
            title = { Text(text = "Xóa công thức") },
            text = { Text(text = "Bạn có muốn xóa không ?") }
        )
    }
}