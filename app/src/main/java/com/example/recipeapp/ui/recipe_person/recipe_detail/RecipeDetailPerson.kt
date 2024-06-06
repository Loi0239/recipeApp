package com.example.recipeapp.ui.recipe_person.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.data.dynamic_data.ingredient.Ingredient
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination

object RecipeDetailPersonDestination: NavigationDestination {
    override val route: String = "Recipe Detail Person"
    const val idRecipe = "itemId"
    val routeWithID = "$route/{$idRecipe}"
}
@Composable
fun RecipeDetailPerson(
    recipeAppViewModel: RecipeAppViewModel,
    recipeDetailPerViewModel: RecipeDetailPerViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    navigateBack:()->Unit
){
    val recipePerson = recipeDetailPerViewModel.recipePerson
    val ingredients: List<Ingredient> = recipeDetailPerViewModel.ingredient ?: emptyList()

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
                Spacer(modifier = Modifier.padding(start = 25.dp))
                Text(
                    text = "Công thức chi tiết",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.padding(top = 15.dp))
        }
        item {
            recipePerson?.let {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = "\uD83D\uDC68\u200D\uD83C\uDF73 Tên món ăn: ${it.nameRecipe}",
                            modifier = Modifier.padding(25.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = "\uD83D\uDD50 Thời gian: ${it.time} phút",
                            modifier = Modifier.padding(25.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = "\uD83D\uDCD1 Danh sách nguyên liệu:",
                            modifier = Modifier.padding(top = 25.dp, bottom = 10.dp, start = 25.dp)
                        )
                        ingredients.forEachIndexed { index, ingredient ->
                            Row(
                                modifier = Modifier.padding(start = 65.dp)
                            ) {
                                Text(text = "✦ ${ingredient.nameIngre}: ${ingredient.weightIngre}")
                                Spacer(modifier = Modifier.padding(top = 5.dp))
                            }
                            Spacer(modifier = Modifier.padding(top = 25.dp))
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = "\uD83C\uDF7D\uFE0F Bước thực hiện:",
                            modifier = Modifier.padding(top = 25.dp, bottom = 10.dp, start = 25.dp)
                        )
                        Text(
                            text = it.step,
                            modifier = Modifier.padding(start = 65.dp)
                        )
                        Spacer(modifier = Modifier.padding(top = 25.dp))
                    }
                }
            }
        }
    }
}