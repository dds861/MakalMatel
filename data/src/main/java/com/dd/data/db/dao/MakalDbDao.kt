package com.dd.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.dd.data.db.entities.CategoryDbData
import com.dd.data.db.entities.MakalDbData


@Dao
interface MakalDbDao : BaseDbDao<MakalDbData> {

    @Query("SELECT * FROM ${MakalDbData.TABLE_NAME}")
    fun getAllMakals(): List<MakalDbData>

}