package com.example.recipeapp.ui.recipe_person.add_recipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object AddRecipeDestination: NavigationDestination {
    override val route: String = "Add recipe"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipe(
    viewModel: AddRecipeViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    navigateBack:()->Unit,
    navToAddRecipe:()->Unit,
    navigateToUpdateRecipe:(Int)->Unit,
){
    var list by remember { mutableStateOf(viewModel.uiState.ingredients.toMutableList()) }
    var nameIngre by remember { mutableStateOf("") }
    var weightIngre by remember { mutableStateOf("") }
    val addRecipeUiState = viewModel.uiState.uiStateRecipe
    val onChangeValue = viewModel::updateUiState
    val coroutineScope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }
    val units = arrayOf("g", "kg", "l", "ml", "quả", "củ", "cây", "lá", "gói")
    var selectedUnit by remember { mutableStateOf(units[0]) }

    LaunchedEffect(viewModel.uiState.ingredients) {
        list = viewModel.uiState.ingredients.toMutableList()
    }

    LazyColumn{
        item {
            Column {
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
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Text(
                        text = "Thêm công thức mới",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp)
                ) {
                    OutlinedTextField(
                        value = addRecipeUiState.nameRecipe,
                        onValueChange = {
                            onChangeValue(
                                addRecipeUiState.copy(nameRecipe = it)
                            )
                        },
                        label = { Text(text = "Tên công thức....") },
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Thời gian thực hiện:",
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.padding(end = 30.dp))
                        OutlinedTextField(
                            value = addRecipeUiState.time,
                            onValueChange = {
                                onChangeValue(
                                    addRecipeUiState.copy(time = it),
                                )
                            },
                            placeholder = { Text(text = "...phút") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .width(120.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Row {
                        Text(
                            text = "Nguyên liệu:",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    OutlinedTextField(
                        value = nameIngre,
                        onValueChange = { nameIngre = it },
                        label = { Text("Tên nguyên liệu") },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = weightIngre,
                            onValueChange = { weightIngre = it },
                            label = { Text("Số lượng") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.width(130.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 20.dp))
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = {
                                expanded = !expanded
                            }
                        ) {
                            OutlinedTextField(
                                value = selectedUnit,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded
                                    )
                                },
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .menuAnchor()
                                    .width(110.dp)
                            )

                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                units.forEach { unit ->
                                    DropdownMenuItem(
                                        text = { Text(text = unit) },
                                        onClick = {
                                            selectedUnit = unit
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            if (nameIngre.isNotBlank() && weightIngre.isNotBlank()) {
                                viewModel.addIngredient(nameIngre, "$weightIngre $selectedUnit")
                                nameIngre = ""
                                weightIngre = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Thêm nguyên liệu")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Text(text = "Danh sách nguyên liệu đã thêm:", fontWeight = FontWeight.Bold)
                    }
                    Column(
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        list.forEach { ingredient ->
                            Text("• ${ingredient.nameIngre}: ${ingredient.weightIngre}")
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Row {
                        Text(
                            text = "Bước thực hiện:",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Row {
                        OutlinedTextField(
                            value = addRecipeUiState.step,
                            onValueChange = {
                                onChangeValue(
                                    addRecipeUiState.copy(step = it)
                                )
                            },
                            placeholder = { Text(text = "Bước 1: ....") },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.addRecipe()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Thêm", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}