package com.example.recipeapp.data.dynamic_data.recipe_person

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CongThucNguoiDung")
data class RecipePerson(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "I_id_cong_thuc_nd")
    val id: Int,
    @ColumnInfo(name = "T_ten_cong_thuc")
    val nameRecipe: String,
    @ColumnInfo(name = "T_thgian")
    val time: String,
    @ColumnInfo(name = "T_buoc_lam")
    val step: String,
)