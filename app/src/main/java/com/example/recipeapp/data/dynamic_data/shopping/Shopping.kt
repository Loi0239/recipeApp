package com.example.recipeapp.data.dynamic_data.shopping

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MuaSam")
data class Shopping (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "I_id_mua_sam")
    val id:Int,
    @ColumnInfo(name = "I_id_san_pham")
    val idProduct:Int,
)