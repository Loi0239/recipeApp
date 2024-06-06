package com.example.recipeapp.ui.recipe_person.update_recipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.data.dynamic_data.ingredient.Ingredient
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object UpdateRecipeDestination: NavigationDestination {
    override val route: String = "Update recipe"
    const val idRecipe = "itemId"
    val routeWithID = "$route/{$idRecipe}"
}

@Composable
fun UpdateRecipe(
    recipeAppViewModel: RecipeAppViewModel,
    updateRecipeViewModel: UpdateRecipeViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    navigateBack:()->Unit
){
    val recipePerson = updateRecipeViewModel.recipePerson
    val ingredients: List<Ingredient> = updateRecipeViewModel.ingredient ?: emptyList()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        recipeAppViewModel.setFooterState(false)
    }
    
    LazyColumn{
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
                Spacer(modifier = Modifier.padding(start = 30.dp))
                Text(
                    text = "Sửa công thức",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
        }
        item {
            recipePerson?.let {
                var nameRecipe by remember { mutableStateOf(it.nameRecipe ?: "") }
                var time by remember { mutableStateOf(it.time ?: "") }
                var step by remember { mutableStateOf(it.step ?: "") }
                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = "\uD83D\uDC68\u200D\uD83C\uDF73 Tên công thức:",
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    OutlinedTextField(
                        value = nameRecipe,
                        onValueChange = { newName ->
                            nameRecipe = newName
                        },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "\uD83D\uDD50 Thời gian:",
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.padding(end = 6.dp))
                        OutlinedTextField(
                            value = time,
                            onValueChange = { newTime->
                                time = newTime
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.width(70.dp)
                        )
                        Text(text = "phút")
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Text(
                        text = "\uD83D\uDCD1 Danh sách nguyên liệu:",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Column(
                        modifier = Modifier.padding(start = 35.dp)
                    ) {
                        ingredients.forEachIndexed { index, ingredient ->
                            var tempName by remember { mutableStateOf(ingredient.nameIngre ?: "") }
                            var tempWeight by remember { mutableStateOf(ingredient.weightIngre ?: "") }

                            if (tempName.isNotEmpty() || tempWeight.isNotEmpty()){
                                Row {
                                    OutlinedTextField(
                                        value = tempName,
                                        onValueChange = { newName ->
                                            tempName = newName
                                            updateRecipeViewModel.updateIngredientName(index, newName)
                                        },
                                        shape = RoundedCornerShape(20.dp),
                                        modifier = Modifier.width(160.dp)
                                    )
                                    Spacer(modifier = Modifier.padding(end = 10.dp))
                                    OutlinedTextField(
                                        value = tempWeight,
                                        onValueChange = { newWeight ->
                                            tempWeight = newWeight
                                            updateRecipeViewModel.updateIngredientWeight(index, newWeight)
                                        },
                                        shape = RoundedCornerShape(20.dp),
                                        modifier = Modifier.width(90.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(top = 15.dp))
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    Text(
                        text = "\uD83C\uDF7D\uFE0F Bước thực hiện:",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(top = 15.dp))
                    OutlinedTextField(
                        value = step,
                        onValueChange = { newStep->
                            step = newStep
                        },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                updateRecipeViewModel.updateIngredients(updateRecipeViewModel.ingredient)
                                val updatedRecipe = updateRecipeViewModel.recipePerson?.copy(
                                    nameRecipe = nameRecipe,
                                    time = time,
                                    step = step,
                                )
                                updatedRecipe?.let {
                                    updateRecipeViewModel.updateRecipe(it)
                                    navigateBack()
                                    Toast.makeText(context, "Sửa công thức thành công", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Sửa", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}