package com.example.recipeapp.data.dynamic_data.schedule

import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    suspend fun insertSchedule(schedule: Schedule)

    suspend fun updateSchedule(schedule: Schedule)

    suspend fun deleteSchedule(schedule: Schedule)

    fun selectScheduleForDate(date:Long): Flow<List<Schedule>>

    fun selectIdProductForDate(date:Long): Flow<List<Int>>

    fun selectPositionsForDate(date: Long): Flow<List<String>>
}