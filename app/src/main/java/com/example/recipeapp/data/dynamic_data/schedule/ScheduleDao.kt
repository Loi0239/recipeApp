package com.example.recipeapp.data.dynamic_data.schedule

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert
    suspend fun insertSchedule(schedule: Schedule)

    @Update
    suspend fun updateSchedule(schedule: Schedule)

    @Delete
    suspend fun deleteSchedule(schedule: Schedule)

    @Query("select * from lichtrinh where L_ngay_duoc_chon = :date")
    fun selectScheduleForDate(date:Long): Flow<List<Schedule>>

    @Query("select I_id_san_pham from lichtrinh where L_ngay_duoc_chon = :date")
    fun selectIdProductForDate(date:Long): Flow<List<Int>>

    @Query("SELECT T_vi_tri_bua_an FROM LichTrinh WHERE L_ngay_duoc_chon = :date")
    fun selectPositionsForDate(date: Long): Flow<List<String>>
}