package com.dd.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = CategoryDbData.TABLE_NAME)
data class CategoryDbData(
        @PrimaryKey
        @ColumnInfo(name = CATEGORY_ID) val category_id: Int,
        @ColumnInfo(name = CATEGORY_TEXT) val category_text: String
) {

    //////////////////////////TABLE///////////////////////////

    companion object {
        const val TABLE_NAME = "catagories"
        const val CATEGORY_ID = "category_id"
        const val CATEGORY_TEXT = "category_text"
    }

    //////////////////////////////////////////////////////////

}