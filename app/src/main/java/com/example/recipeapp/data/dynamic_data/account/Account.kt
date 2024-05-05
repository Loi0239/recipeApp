package com.example.recipeapp.data.dynamic_data.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TaiKhoan")
data class Account(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "I_id_tai_khoan")
    val id: Int,
    @ColumnInfo(name = "T_ten_tai_khoan")
    val userName: String,
    @ColumnInfo(name = "T_mat_khau")
    val password: String,
)