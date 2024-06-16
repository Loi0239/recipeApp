package com.example.recipeapp.ui.schedule

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.dynamic_data.favourite.Favourite
import com.example.recipeapp.data.dynamic_data.schedule.Schedule
import com.example.recipeapp.data.dynamic_data.schedule.ScheduleRepository
import com.example.recipeapp.data.static_data.Products
import com.example.recipeapp.ui.recipe.IngredientDestination
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import java.time.LocalDate


class ScheduleViewModel(
    private val scheduleRepository: ScheduleRepository
):ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    fun updateIsDatePickerDialogOpen(value:Boolean){
        uiState = uiState.copy(isDatePickerDialogOpen = value)
    }

    fun updateIsDateSelectAndDatePicker(value: Boolean){
        uiState = uiState.copy(isDateSelectAndDatePicker = value)
    }

    fun updateIsBottomSheetOpen(value: Boolean){
        uiState = uiState.copy(isBottomSheetOpen = value)
    }

    fun updateIsBottomSheetAdd(value: Boolean){
        uiState = uiState.copy(isBottomSheetAdd = value)
    }

    fun updateIsSelectMealOpen(value: Boolean){
        uiState = uiState.copy(isSelectMealOpen = value)
    }

    fun updateIsPeopleOpen(value: Boolean){
        uiState = uiState.copy(isPeopleOpen = value)
    }

    fun updateSelectDate(value: LocalDate){
        uiState = uiState.copy(selectedDate = value)
    }

    fun updateNameProduct(idProduct: Int){
        Log.i("check", "$idProduct")
        if(idProduct >=0){
            uiState = uiState.copy(nameProduct = Products().getNameProduct(idProduct))
        }else{
            uiState = uiState.copy(nameProduct = "Lựa chọn công thức")
        }
    }

    fun updateMealValue(value:String){
        uiState = uiState.copy(mealValue = value)
    }

    fun updateColorMeal(value: Color){
        uiState = uiState.copy(colorMeal = value)
    }

    fun updateNumberPeople(value: Int){
        uiState = uiState.copy(numberPeople = value)
    }

    fun updateNote(value:String){
        uiState = uiState.copy(note = value)
    }

    fun updateIdSchedule(value: Int){
        uiState = uiState.copy(idSchedule = value)
    }

    fun getColorForPosition(position: String):Color{
        return when(position){
            "Bữa sáng" -> {Color.Red}
            "Bữa trưa" -> {Color.Yellow}
            "Bữa xế" -> {Color.Green}
            "Bữa chiều" -> {Color.Gray}
            "Bữa tối" -> {Color.Blue}
            else -> {Color.Black}
        }
    }

    fun getListSchedule(date: Long): Flow<List<Schedule>>{
        return scheduleRepository.selectScheduleForDate(date)
    }

    fun getPositionsForDate(date: Long): Flow<List<String>> {
        return scheduleRepository.selectPositionsForDate(date)
    }

    suspend fun addSchedule(idProduct: Int){
        val dueDate: Long = uiState.selectedDate.toLong()
        if(idProduct >= 0){
            scheduleRepository.insertSchedule(
                uiState.uiSchedule.addSchedule(
                    position = uiState.mealValue,
                    description = uiState.note,
                    numberPeople = uiState.numberPeople,
                    dueDate = dueDate,
                    idProduct = idProduct
                )
            )
        }
    }

    suspend fun updateSchedule(idProduct: Int){
        scheduleRepository.updateSchedule(
            Schedule(
                id = uiState.idSchedule,
                position = uiState.mealValue,
                description = uiState.note,
                numberPeople = uiState.numberPeople,
                dueDate = uiState.selectedDate.toLong(),
                idProduct = idProduct
            )
        )
    }

    suspend fun deleteSchedule(idProduct: Int){
        scheduleRepository.deleteSchedule(
            Schedule(
                id = uiState.idSchedule,
                position = uiState.mealValue,
                description = uiState.note,
                numberPeople = uiState.numberPeople,
                dueDate = uiState.selectedDate.toLong(),
                idProduct = idProduct
            )
        )
    }
}

data class UiState(
    val uiSchedule: UiSchedule = UiSchedule(),
    var isDatePickerDialogOpen:Boolean = false,
    var isDateSelectAndDatePicker:Boolean = false,
    var isBottomSheetOpen:Boolean = false,
    var isBottomSheetAdd:Boolean = false,
    var isSelectMealOpen:Boolean = false,
    var isPeopleOpen:Boolean = false,
    var selectedDate: LocalDate = Instant.now().toEpochMilli().toLocalDate(),
    var nameProduct:String = "Lựa chọn công thức",
    var mealValue:String = "bữa sáng",
    var colorMeal:Color = Color.Red,
    var numberPeople:Int = 1,
    var note: String = "",
    var idSchedule: Int = 0,
)

data class UiSchedule(
    val id:Int = 0,
    var position:String = "",
    var description:String = "",
    var numberPeople:Int = 0,
    var dueDate:Long = 0,
    var idProduct:Int = 0,
)

private fun UiSchedule.addSchedule(
    position: String,
    description: String,
    numberPeople: Int,
    dueDate: Long,
    idProduct:Int,
):Schedule = Schedule(
    id = id,
    position = position,
    description = description,
    numberPeople = numberPeople,
    dueDate = dueDate,
    idProduct = idProduct
)

