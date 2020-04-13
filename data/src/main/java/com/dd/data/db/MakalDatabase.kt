package com.dd.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dd.data.db.dao.MakalDbDao
import com.dd.data.db.dao.CategoryDbDao
import com.dd.data.db.entities.CategoryDbData
import com.dd.data.db.entities.MakalDbData


@Database(
        entities = [
            MakalDbData::class,
            CategoryDbData::class
        ],
        version = 1,
        exportSchema = true
)
abstract class MakalDatabase : RoomDatabase() {
    abstract fun makalDao(): MakalDbDao
    abstract fun categoryDao(): CategoryDbDao
}