package com.dd.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.dd.data.db.entities.MakalDbData

@Dao
interface MakalDbDao : BaseDbDao<MakalDbData> {
    @Query("SELECT * FROM ${MakalDbData.TABLE_NAME}")
    fun getAllMakals(): List<MakalDbData>

    @Query("SELECT * FROM ${MakalDbData.TABLE_NAME} WHERE ${MakalDbData.CATEGORY_ID}=:categoryId")
    fun getMakalsByCategoryId(categoryId: Int): List<MakalDbData>

    @Query("SELECT * FROM ${MakalDbData.TABLE_NAME} WHERE ${MakalDbData.MAKAL_TEXT} LIKE '%' || :queryText|| '%'")
    fun getMakalsByQueryText(queryText: String): List<MakalDbData>
}