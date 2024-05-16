package com.example.recipeapp.data.dynamic_data.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.recipeapp.data.dynamic_data.recipe_person.RecipePerson

@Entity(tableName = "NguyenLieu",
    foreignKeys = [
        ForeignKey(
            entity = RecipePerson::class,
            parentColumns = ["I_id_cong_thuc_nd"],
            childColumns = ["I_id_cong_thuc_nd"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "I_id_nguyen_lieu")
    val id:Int,
    @ColumnInfo(name = "T_ten_nguyen_lieu")
    val nameIngre:String,
    @ColumnInfo(name = "T_trong_luong")
    val weightIngre:String,
    @ColumnInfo(name = "I_id_cong_thuc_nd")
    val idRecPer:Int,
)