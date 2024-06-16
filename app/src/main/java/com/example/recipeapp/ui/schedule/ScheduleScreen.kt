package com.example.recipeapp.ui.schedule

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.data.dynamic_data.schedule.Schedule
import com.example.recipeapp.data.static_data.Product
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.RecipeAppViewModel
import com.example.recipeapp.ui.navigation.NavigationDestination
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object ScheduleDestination:NavigationDestination{
    override val route: String = "schedule"
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    navToFindForSchedule:()->Unit,
    navToBack:()->Unit,
    viewModel: ScheduleViewModel = viewModel(factory = RecipeAppViewModel.Factory),
    recipeAppViewModel: RecipeAppViewModel,
) {
    val uiState = viewModel.uiState

    val listSchedule by viewModel.getListSchedule(uiState.selectedDate.toLong())
        .collectAsState(initial = emptyList())

    val datePickerState = rememberDatePickerState(
        initialDisplayedMonthMillis = Instant.now().toEpochMilli()
    )

    var weekAround = uiState.selectedDate.getWeekAround()

    fun updateSelectedDate(newDate: LocalDate) {
        viewModel.updateSelectDate(newDate)
        weekAround = uiState.selectedDate.getWeekAround()
    }

    viewModel.updateNameProduct(recipeAppViewModel.idUiState.idProductAddSchedule)

    ScheduleDatePicker(
        state = datePickerState,
        isOpen = uiState.isDatePickerDialogOpen,
        onDismissRequest = {
            viewModel.updateIsDatePickerDialogOpen(false)
            viewModel.updateIsDateSelectAndDatePicker(true) },
        onConfirmButtonClicked = {
            datePickerState.selectedDateMillis?.let { millis ->
                val newDate = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                updateSelectedDate(newDate)
            }
            viewModel.updateIsDateSelectAndDatePicker(true)
            viewModel.updateIsDatePickerDialogOpen(false)
        }
    )

    ScheduleBottomSheet(
        isOpen = uiState.isBottomSheetOpen,
        onDismissRequest = {viewModel.updateIsBottomSheetOpen(false)},
        navToFindForSchedule = navToFindForSchedule,
        selectedDate = uiState.selectedDate,
        mealValue = uiState.mealValue,
        colorMeal = uiState.colorMeal,
        numberPeople = uiState.numberPeople,
        note = uiState.note,
        onChangeOpenSchedule = { viewModel.updateIsDatePickerDialogOpen(true) },
        onChangeOpenSelectMeal = { viewModel.updateIsSelectMealOpen(true) },
        onChangeOpenPeople = { viewModel.updateIsPeopleOpen(true) },
        onChangeNote = {viewModel.updateNote(value = it)},
        viewModel = viewModel,
        recipeAppViewModel = recipeAppViewModel
    )

    ScheduleSelectMeal(
        isOpen = uiState.isSelectMealOpen,
        onDismissRequest = { viewModel.updateIsSelectMealOpen(false) },
        onChangeMeal = { colorValue,newValue ->
            viewModel.updateMealValue(newValue)
            viewModel.updateColorMeal(colorValue)
            viewModel.updateIsSelectMealOpen(false)
        }
    )

    SchedulePeople(
        isOpen = uiState.isPeopleOpen,
        onDismissRequest = { viewModel.updateIsPeopleOpen(false) },
        onChangeIsOpen = {  viewModel.updateIsPeopleOpen(false)},
        onChangeNumberPeople = { newValue ->
            viewModel.updateNumberPeople(newValue)
        },
        numberPeople = uiState.numberPeople,
    )

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
                    IconButton(onClick = navToBack ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                        )
                    }
                },
                actions = {
                    IconButton(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(colorResource(id = R.color.primaryColor)),
                        onClick = {
                            recipeAppViewModel.updateIdProductSchedule(-1)
                            viewModel.updateMealValue("Bữa sáng")
                            viewModel.updateColorMeal(Color.Red)
                            viewModel.updateNumberPeople(1)
                            viewModel.updateNote("")
                            viewModel.updateIsBottomSheetAdd(true)
                            viewModel.updateIsBottomSheetOpen(true)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add recipe",
                            modifier = Modifier.size(30.dp),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier.padding(paddingValue)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Chọn ngày:",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Color.LightGray)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (uiState.isDateSelectAndDatePicker) {
                            datePickerState.selectedDateMillis.changeMillisToDateString()
                        } else {
                            uiState.selectedDate.format(
                                DateTimeFormatter
                                    .ofPattern("dd/MM/yyyy", Locale.getDefault())
                            ) ?: ""
                        },
                        style = MaterialTheme.typography.headlineSmall
                    )
                    IconButton(
                        onClick = { viewModel.updateIsDatePickerDialogOpen(true) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "select due date"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                weekAround.let { dates ->
                    dates.forEach { date ->
                        val day = date.format(
                            DateTimeFormatter.ofPattern(
                                "EEEE",
                                Locale.getDefault()
                            )
                        )

                        item {
                            PickDateOfWeek(
                                date = date.toLong(),
                                day = day,
                                isSelected = date == uiState.selectedDate,
                                onClick = {
                                    updateSelectedDate(date)
                                    viewModel.updateIsDateSelectAndDatePicker(false)
                                },
                                viewModel = viewModel,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.padding(bottom = 44.dp)
            ) {
                items(
                    items = listSchedule
                ) {schedule ->
                    val product = Products().getProduct(schedule.idProduct)

                    RecipeCard(
                        schedule = schedule,
                        product = product,
                        viewModel = viewModel,
                        recipeAppViewModel = recipeAppViewModel,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

}

@Composable
fun PickDateOfWeek(
    date: Long,
    day:String,
    isSelected:Boolean,
    onClick: () -> Unit,
    viewModel: ScheduleViewModel,
    modifier: Modifier = Modifier
){
    val positions by viewModel.getPositionsForDate(date).collectAsState(initial = emptyList())

    var selectDay by remember { mutableStateOf("") }
    selectDay = when (day.uppercase(Locale.getDefault())) {
        "MONDAY" -> "thứ hai"
        "TUESDAY" -> "thứ ba"
        "WEDNESDAY" -> "thứ tư"
        "THURSDAY" -> "thứ năm"
        "FRIDAY" -> "thứ sáu"
        "SATURDAY" -> "thứ bảy"
        "SUNDAY" -> "chủ nhật"
        else -> day
    }

    Column(
        modifier = modifier
            .size(80.dp, 100.dp)
            .clickable(onClick = onClick)
            .let {
                if (isSelected) {
                    it.background(color = colorResource(id = R.color.primaryColor))
                } else {
                    it.border(1.dp, colorResource(id = R.color.primaryColor))
                }
            }
            .padding(vertical = 4.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = selectDay,
            style = MaterialTheme.typography.titleLarge,
            color = if(isSelected){
                Color.White
            }else{
                colorResource(id = R.color.primaryColor)
            },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn{
            items(
                items = positions
            ){position ->
                val color = viewModel.getColorForPosition(position)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .padding(horizontal = 4.dp)
                        .clip(CircleShape)
                        .background(color)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecipeCard(
    schedule: Schedule,
    product:Product,
    viewModel: ScheduleViewModel,
    recipeAppViewModel: RecipeAppViewModel,
){
    val color = viewModel.getColorForPosition(schedule.position)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp)
            .clickable {
                viewModel.updateNameProduct(schedule.idProduct)
                viewModel.updateSelectDate(schedule.dueDate.toLocalDate())
                viewModel.updateMealValue(schedule.position)
                viewModel.updateColorMeal(viewModel.getColorForPosition(schedule.position))
                viewModel.updateNumberPeople(schedule.numberPeople)
                viewModel.updateNote(schedule.description)
                viewModel.updateIdSchedule(schedule.id)
                recipeAppViewModel.updateIdProductSchedule(schedule.idProduct)
                viewModel.updateIsBottomSheetAdd(false)
                viewModel.updateIsBottomSheetOpen(true)

            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(color)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Image(
            painter = painterResource(id = product.image),
            contentDescription = "image food",
            contentScale = ContentScale.Crop,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
        ) {
            Text(
                text = schedule.position,
                style = MaterialTheme.typography.headlineLarge,
                color = color,
            )

            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineLarge
            )

            if(schedule.description.isNotEmpty()){
                Text(
                    text = schedule.description,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Text(
                text = "${schedule.numberPeople} người",
                style = MaterialTheme.typography.titleSmall,
            )

            Text(
                text = "Thời gian : ${product.timeComplete} phút",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ScheduleDatePicker(
//    state: DatePickerState,
//    isOpen: Boolean,
//    confirmButtonText: String = "Ok",
//    dismissButtonText: String = "Cancel",
//    onDismissRequest:()->Unit,
//    onConfirmButtonClicked:()->Unit,
//){
//    if(isOpen){
//        DatePickerDialog(
//            onDismissRequest = onDismissRequest,
//            confirmButton = {
//                TextButton(onClick = onConfirmButtonClicked) {
//                    Text(text = confirmButtonText)
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = onDismissRequest) {
//                    Text(text = dismissButtonText)
//                }
//            }
//        ) {
//            DatePicker(
//                state = state,
//                dateValidator = { timestamp ->
//                    val selectedDate = Instant
//                        .ofEpochMilli(timestamp)
//                        .atZone(ZoneId.systemDefault())
//                        .toLocalDate()
//                    val currentDate = LocalDate.now(ZoneId.systemDefault())
//                    selectedDate >= currentDate
//                }
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ScheduleBottomSheet(
//    isOpen: Boolean,
//    bottomSheetTitle:String = "Thêm / chỉnh sửa kế hoạch",
//    onDismissRequest: () -> Unit,
//    navToFindForSchedule:()->Unit,
//    onChangeOpenSchedule:()->Unit,
//    onChangeOpenSelectMeal:()->Unit,
//    onChangeOpenPeople:()->Unit,
//) {
//    if (isOpen) {
//        ModalBottomSheet(
//            onDismissRequest = onDismissRequest,
//            dragHandle = {
//                Text(
//                    text = bottomSheetTitle,
//                    style = MaterialTheme.typography.displaySmall,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp),
//                    textAlign = TextAlign.Center
//                )
//            }
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Button(
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 16.dp)
//                        .width(300.dp),
//                    colors = ButtonDefaults.buttonColors(Color.Unspecified),
//                    shape = RoundedCornerShape(8.dp),
//                    border = ButtonDefaults.outlinedButtonBorder,
//                    onClick = navToFindForSchedule
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "search recipe",
//                        tint = Color.Black
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Lựa chọn công thức",
//                        style = MaterialTheme.typography.headlineSmall,
//                        color = Color.Black
//                    )
//                }
//
//                Button(
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 16.dp)
//                        .width(300.dp),
//                    colors = ButtonDefaults.buttonColors(Color.Unspecified),
//                    shape = RoundedCornerShape(8.dp),
//                    border = ButtonDefaults.outlinedButtonBorder,
//                    onClick = { onChangeOpenSchedule() }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.DateRange,
//                        contentDescription = "select date",
//                        tint = Color.Black
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "chủ nhật, 19/5/2004",
//                        style = MaterialTheme.typography.headlineSmall,
//                        color = Color.Black
//                    )
//                }
//
//                Button(
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 16.dp)
//                        .width(300.dp),
//                    colors = ButtonDefaults.buttonColors(Color.Unspecified),
//                    shape = RoundedCornerShape(8.dp),
//                    border = ButtonDefaults.outlinedButtonBorder,
//                    onClick = onChangeOpenSelectMeal
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Menu,
//                        contentDescription = "select meal",
//                        tint = Color.Black
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "bữa sáng",
//                        style = MaterialTheme.typography.headlineSmall,
//                        color = Color.Black
//                    )
//                    Spacer(modifier = Modifier.weight(1f))
//                }
//
//                Button(
//                    modifier = Modifier
//                        .padding(vertical = 8.dp, horizontal = 16.dp)
//                        .width(300.dp),
//                    colors = ButtonDefaults.buttonColors(Color.Unspecified),
//                    shape = RoundedCornerShape(8.dp),
//                    border = ButtonDefaults.outlinedButtonBorder,
//                    onClick = onChangeOpenPeople
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Person,
//                        contentDescription = "people number",
//                        tint = Color.Black
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "1 người",
//                        style = MaterialTheme.typography.headlineSmall,
//                        color = Color.Black
//                    )
//                    Spacer(modifier = Modifier.weight(1f))
//                }
//
//                OutlinedTextField(
//                    value = "",
//                    onValueChange = {},
//                    placeholder = { Text(text = "ghi chú cho công thức")},
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.Done
//                    ),
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        focusedBorderColor = colorResource(id = R.color.primaryColor),
//                        unfocusedBorderColor = Color.Gray
//                    ),
//                    minLines = 2,
//                    modifier = Modifier
//                        .width(350.dp),
//                )
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Button(
//                        modifier = Modifier
//                            .padding(vertical = 8.dp, horizontal = 16.dp)
//                            .width(150.dp),
//                        colors = ButtonDefaults.buttonColors(Color.Unspecified),
//                        shape = RoundedCornerShape(8.dp),
//                        border = ButtonDefaults.outlinedButtonBorder,
//                        onClick = { /*TODO*/ }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Clear,
//                            contentDescription = "remove",
//                            tint = Color.Black,
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(
//                            text = "xóa",
//                            style = MaterialTheme.typography.headlineSmall,
//                            color = Color.Black,
//                            modifier = Modifier.padding(bottom = 4.dp)
//                        )
//                    }
//
//                    Button(
//                        modifier = Modifier
//                            .padding(vertical = 8.dp, horizontal = 16.dp)
//                            .width(150.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            colorResource(id = R.color.primaryColor)),
//                        shape = RoundedCornerShape(8.dp),
//                        border = ButtonDefaults.outlinedButtonBorder,
//                        onClick = { /*TODO*/ }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Check,
//                            contentDescription = "save",
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(
//                            text = "thêm",
//                            style = MaterialTheme.typography.headlineSmall,
//                            modifier = Modifier.padding(bottom = 4.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ScheduleSelectMeal(
//    isOpen: Boolean,
//    bottomSheetTitle:String = "lựa chọn bữa ăn",
//    onDismissRequest: () -> Unit,
//    onChangeMeal:()->Unit,
//){
//    val listMeal = listOf(
//        Pair(Color.Red, "bữa sáng"),
//        Pair(Color.Yellow, "bữa trưa"),
//        Pair(Color.Green, "bữa xế"),
//        Pair(Color.Gray, "bữa chiều"),
//        Pair(Color.Blue, "bữa tối"),
//    )
//
//    if(isOpen){
//        ModalBottomSheet(
//            onDismissRequest = onDismissRequest,
//            dragHandle = {
//                Text(
//                    text = bottomSheetTitle,
//                    style = MaterialTheme.typography.displaySmall,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp),
//                    textAlign = TextAlign.Center
//                )
//                Divider(
//                    color = Color.Gray,
//                    thickness = 1.dp,
//                )
//            }
//        ) {
//            Column{
//                for(meal in listMeal){
//                    ScehduleSelectMealCard(
//                        color = meal.first,
//                        text = meal.second,
//                        onChangeMeal = onChangeMeal,
//
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ScehduleSelectMealCard(
//    color:Color,
//    text:String,
//    onChangeMeal:()->Unit,
//){
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(50.dp)
//            .padding(horizontal = 16.dp)
//            .clip(shape = RoundedCornerShape(8.dp))
//            .border(1.dp, Color.Gray)
//            .clickable {
//                onChangeMeal()
//            },
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxHeight()
//                .width(8.dp)
//                .background(color)
//        )
//
//        Text(
//            text = text,
//            style = MaterialTheme.typography.headlineSmall,
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center
//        )
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SchedulePeople(
//    isOpen: Boolean,
//    bottomSheetTitle:String = "lựa chọn số người",
//    onDismissRequest: () -> Unit,
//    onChangeIsOpen:()->Unit,
//){
//    var centerValue by remember { mutableStateOf(1) }
//    if(isOpen){
//        ModalBottomSheet(
//            onDismissRequest = onDismissRequest,
//            dragHandle = {
//                Text(
//                    text = bottomSheetTitle,
//                    style = MaterialTheme.typography.displaySmall,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp),
//                    textAlign = TextAlign.Center
//                )
//                Divider(
//                    color = Color.Gray,
//                    thickness = 1.dp,
//                )
//            }
//        ) {
//            Column {
//                CounterWithSliding(
//                    centerValue = centerValue,
//                    onChangeCenterValue = { newValue ->
//                        centerValue = newValue
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Spacer(modifier = Modifier.weight(1f))
//                    Button(
//                        modifier = Modifier
//                            .padding(vertical = 8.dp, horizontal = 16.dp)
//                            .width(150.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            colorResource(id = R.color.primaryColor)),
//                        shape = RoundedCornerShape(8.dp),
//                        border = ButtonDefaults.outlinedButtonBorder,
//                        onClick = onChangeIsOpen
//                    ) {
//                        Text(
//                            text = "xác nhận",
//                            style = MaterialTheme.typography.headlineLarge,
//                            color = Color.White
//                        )
//                    }
//                }
//            }
//
//        }
//    }
//}
//
//@Composable
//fun CounterWithSliding(
//    centerValue:Int,
//    onChangeCenterValue:(Int)->Unit
//) {
//    val valuesToShow = if (centerValue == 1) {
//        listOf(1, 2)
//    } else {
//        (centerValue - 1..centerValue + 1).filter { it > 0 }
//    }
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        for (i in valuesToShow) {
//            Text(
//                text = i.toString(),
//                color = Color.Black,
//                modifier = Modifier
//                    .clickable { onChangeCenterValue(i) }
//                    .padding(16.dp)
//            )
//            if(i < centerValue + 1){
//                Divider(
//                    color = Color.Gray,
//                    thickness = 1.dp,
//                    modifier = Modifier.width(12.dp)
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//    }
//}


//@Preview(showSystemUi = true)
//@Composable
//fun Preview(){
//    ScheduleScreen(
//        navToFindForSchedule = {},
//        navToBack = {}
//    )
//}