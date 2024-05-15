package com.example.recipeapp.data.dynamic_data.favourite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "YeuThich")
data class Favourite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "I_id_yeu_thich")
    val id:Int,
    @ColumnInfo(name = "I_id_san_pham")
    val idProduct:Int,
)