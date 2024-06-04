package com.example.recipeapp.data.dynamic_data.recipe_person

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipePersonDao {
    @Insert
    suspend fun insertRecipePerson(recipePerson: RecipePerson)

    // Hiển thị danh sách công thức của người dùng
    @Query("SELECT * FROM CongThucNguoiDung ORDER BY I_id_cong_thuc_nd DESC")
    fun getAll(): Flow<List<RecipePerson>>

    @Delete
    suspend fun deleteRecipePerson(recipePerson: RecipePerson)

    // Lấy danh sách cụ từ id cụ thể để sửa
    @Query("SELECT * from CongThucNguoiDung WHERE I_id_cong_thuc_nd = :id")
    fun getItemRecipeStream(id: Int): Flow<RecipePerson>

    // Hiển thị danh sách cụ thể
    @Query("SELECT * from CongThucNguoiDung WHERE I_id_cong_thuc_nd = :id")
    fun getRecipeDetail(id: Int): Flow<RecipePerson>

    @Update
    suspend fun updateRecipePerson(recipePerson: RecipePerson)

    // Lấy id của công thức vừa thêm vào để thêm nguyên liêu
    @Query("SELECT I_id_cong_thuc_nd FROM CongThucNguoiDung ORDER BY I_id_cong_thuc_nd DESC LIMIT 1")
    suspend fun getLastInsertId(): Int

    @Query("SELECT COUNT(*) AS SoLuongDong FROM CongThucNguoiDung")
    suspend fun getCount():Int
}