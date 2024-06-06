package com.example.recipeapp.data.dynamic_data.schedule

import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(private val scheduleDao: ScheduleDao):ScheduleRepository {
    override suspend fun insertSchedule(schedule: Schedule) =
        scheduleDao.insertSchedule(schedule)

    override suspend fun updateSchedule(schedule: Schedule) =
        scheduleDao.updateSchedule(schedule)

    override suspend fun deleteSchedule(schedule: Schedule) =
        scheduleDao.deleteSchedule(schedule)

    override fun selectScheduleForDate(date: Long): Flow<List<Schedule>> =
        scheduleDao.selectScheduleForDate(date)

    override fun selectIdProductForDate(date: Long): Flow<List<Int>> =
        scheduleDao.selectIdProductForDate(date)

    override fun selectPositionsForDate(date: Long): Flow<List<String>> =
        scheduleDao.selectPositionsForDate(date)
}