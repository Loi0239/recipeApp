package com.example.recipeapp.ui.schedule

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R
import com.example.recipeapp.ui.RecipeAppViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleDatePicker(
    state: DatePickerState,
    isOpen: Boolean,
    confirmButtonText: String = "Ok",
    dismissButtonText: String = "Cancel",
    onDismissRequest:()->Unit,
    onConfirmButtonClicked:()->Unit,
){
    if(isOpen){
        DatePickerDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(onClick = onConfirmButtonClicked) {
                    Text(text = confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = dismissButtonText)
                }
            }
        ) {
            DatePicker(
                state = state,
                dateValidator = { timestamp ->
                    val selectedDate = Instant
                        .ofEpochMilli(timestamp)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                    val currentDate = LocalDate.now(ZoneId.systemDefault())
                    selectedDate >= currentDate
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleBottomSheet(
    isOpen: Boolean,
    bottomSheetTitle:String = "Thêm / chỉnh sửa kế hoạch",
    onDismissRequest: () -> Unit,
    navToFindForSchedule:()->Unit,
    selectedDate:LocalDate?,
    mealValue:String,
    colorMeal:Color,
    numberPeople:Int,
    note:String,
    onChangeOpenSchedule:()->Unit,
    onChangeOpenSelectMeal:()->Unit,
    onChangeOpenPeople:()->Unit,
    onChangeNote:(String)->Unit,
    viewModel: ScheduleViewModel,
    recipeAppViewModel: RecipeAppViewModel
) {
    if (isOpen) {
        val day = selectedDate?.format(
            DateTimeFormatter.ofPattern(
                "EEEE",
                Locale.getDefault()
            )
        )

        val coroutineScope = rememberCoroutineScope()

        val dayConvertVietnamese = when (day?.uppercase(Locale.getDefault())) {
            "MONDAY" -> "thứ hai"
            "TUESDAY" -> "thứ ba"
            "WEDNESDAY" -> "thứ tư"
            "THURSDAY" -> "thứ năm"
            "FRIDAY" -> "thứ sáu"
            "SATURDAY" -> "thứ bảy"
            "SUNDAY" -> "chủ nhật"
            else -> ""
        }

        val date = selectedDate?.format(
            DateTimeFormatter
                .ofPattern("dd/MM/yyyy", Locale.getDefault())
        ) ?: ""

        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            dragHandle = {
                Text(
                    text = bottomSheetTitle,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .width(310.dp),
                    colors = ButtonDefaults.buttonColors(Color.Unspecified),
                    shape = RoundedCornerShape(8.dp),
                    border = ButtonDefaults.outlinedButtonBorder,
                    onClick = navToFindForSchedule
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search recipe",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = viewModel.uiState.nameProduct,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Button(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .width(310.dp),
                    colors = ButtonDefaults.buttonColors(Color.Unspecified),
                    shape = RoundedCornerShape(8.dp),
                    border = ButtonDefaults.outlinedButtonBorder,
                    onClick = { onChangeOpenSchedule() }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "select date",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$dayConvertVietnamese, $date",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Button(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .width(310.dp),
                    colors = ButtonDefaults.buttonColors(Color.Unspecified),
                    shape = RoundedCornerShape(8.dp),
                    border = ButtonDefaults.outlinedButtonBorder,
                    onClick = onChangeOpenSelectMeal
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "select meal",
                        tint = colorMeal
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = mealValue,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Button(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .width(310.dp),
                    colors = ButtonDefaults.buttonColors(Color.Unspecified),
                    shape = RoundedCornerShape(8.dp),
                    border = ButtonDefaults.outlinedButtonBorder,
                    onClick = onChangeOpenPeople
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "people number",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$numberPeople người",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                OutlinedTextField(
                    value = note,
                    onValueChange = {onChangeNote(it)},
                    placeholder = { Text(text = "ghi chú cho công thức") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.primaryColor),
                        unfocusedBorderColor = Color.Gray
                    ),
                    minLines = 2,
                    modifier = Modifier
                        .width(350.dp),
                )

                if(viewModel.uiState.isBottomSheetAdd){
                    Button(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .width(150.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(id = R.color.primaryColor)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = ButtonDefaults.outlinedButtonBorder,
                        onClick = {
                            coroutineScope.launch {
                                viewModel.addSchedule(recipeAppViewModel.idUiState.idProductAddSchedule)
                                viewModel.updateIsBottomSheetOpen(false)
                            }

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "save",
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "thêm",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }else{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                                .width(150.dp),
                            colors = ButtonDefaults.buttonColors(Color.Unspecified),
                            shape = RoundedCornerShape(8.dp),
                            border = ButtonDefaults.outlinedButtonBorder,
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.deleteSchedule(recipeAppViewModel.idUiState.idProductAddSchedule)
                                    viewModel.updateIsBottomSheetOpen(false)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "remove",
                                tint = Color.Black,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "xóa",
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color.Black,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }

                        Button(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                                .width(150.dp),
                            colors = ButtonDefaults.buttonColors(
                                colorResource(id = R.color.primaryColor)
                            ),
                            shape = RoundedCornerShape(8.dp),
                            border = ButtonDefaults.outlinedButtonBorder,
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.updateSchedule(recipeAppViewModel.idUiState.idProductAddSchedule)
                                    viewModel.updateIsBottomSheetOpen(false)
                                }

                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "update",
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "sửa",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleSelectMeal(
    isOpen: Boolean,
    bottomSheetTitle:String = "lựa chọn bữa ăn",
    onDismissRequest: () -> Unit,
    onChangeMeal:(Color,String)->Unit,
){
    val listMeal = listOf(
        Pair(Color.Red, "bữa sáng"),
        Pair(Color.Yellow, "bữa trưa"),
        Pair(Color.Green, "bữa xế"),
        Pair(Color.Gray, "bữa chiều"),
        Pair(Color.Blue, "bữa tối"),
    )

    if(isOpen){
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            dragHandle = {
                Text(
                    text = bottomSheetTitle,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                )
            }
        ) {
            Column{
                for(meal in listMeal){
                    ScehduleSelectMealCard(
                        color = meal.first,
                        text = meal.second,
                        onChangeMeal = {
                            onChangeMeal(meal.first,meal.second) },
                        )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun ScehduleSelectMealCard(
    color: Color,
    text:String,
    onChangeMeal:()->Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray)
            .clickable {
                onChangeMeal()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
                .background(color)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchedulePeople(
    isOpen: Boolean,
    bottomSheetTitle:String = "lựa chọn số người",
    onDismissRequest: () -> Unit,
    onChangeIsOpen:()->Unit,
    onChangeNumberPeople:(Int)->Unit,
    numberPeople:Int,
){
    if(isOpen){
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            dragHandle = {
                Text(
                    text = bottomSheetTitle,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                )
            }
        ) {
            Column {
                CounterWithSliding(
                    numberPeople = numberPeople,
                    onChangeNumberPeople = onChangeNumberPeople
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .width(150.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(id = R.color.primaryColor)),
                        shape = RoundedCornerShape(8.dp),
                        border = ButtonDefaults.outlinedButtonBorder,
                        onClick = onChangeIsOpen
                    ) {
                        Text(
                            text = "xác nhận",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun CounterWithSliding(
    numberPeople:Int,
    onChangeNumberPeople:(Int)->Unit
) {
    val valuesToShow = if (numberPeople == 1) {
        listOf(1, 2)
    } else {
        (numberPeople - 1..numberPeople + 1).filter { it > 0 }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        for (i in valuesToShow) {
            Text(
                text = i.toString(),
                color = Color.Black,
                modifier = Modifier
                    .clickable { onChangeNumberPeople(i) }
                    .padding(16.dp)
            )
            if(i < numberPeople + 1){
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.width(12.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}