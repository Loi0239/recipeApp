package com.example.recipeapp.data.dynamic_data.schedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LichTrinh")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "I_id_lich_trinh")
    val id:Int,
    @ColumnInfo(name = "T_vi_tri_bua_an")
    val position:String,
    @ColumnInfo(name = "T_mo_ta")
    val description:String,
    @ColumnInfo(name = "I_so_nguoi")
    val numberPeople:Int,
    @ColumnInfo(name = "L_ngay_duoc_chon")
    val dueDate:Long,
    @ColumnInfo(name = "I_id_san_pham")
    val idProduct:Int,
)
