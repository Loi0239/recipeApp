package com.example.recipeapp.ui.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun Long?.changeMillisToDateString():String{
    val date: LocalDate = this?.let {
        Instant
            .ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }?:LocalDate.now()
    return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toLocalDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()

}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toLong(): Long {
    return atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.getWeekAround(): List<LocalDate> {
    val currentDate = LocalDate.now()
    return if(this.isAfter(currentDate.minusDays(1))){
        (this.minusDays(2)..this.plusDays(4)).toList()
    }else if(this.isAfter(currentDate.minusDays(2))){
        (this.minusDays(1)..this.plusDays(5)).toList()
    }else{
        (this..this.plusDays(6)).toList()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
operator fun LocalDate.rangeTo(other: LocalDate) = generateSequence(this) { date ->
    if (date < other) date.plusDays(1) else null
}.toList()