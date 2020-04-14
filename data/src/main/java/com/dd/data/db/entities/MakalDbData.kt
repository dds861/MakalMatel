package com.dd.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = MakalDbData.TABLE_NAME)
data class MakalDbData(

        @PrimaryKey
        @ColumnInfo(name = ID) val id: Long,
        @ColumnInfo(name = CATEGORY_ID) val category_id: Int,
        @ColumnInfo(name = MAKAL_TEXT) val makal_text: String
) {

    //////////////////////////TABLE///////////////////////////

    companion object {
        const val TABLE_NAME = "makals"
        const val ID = "id"
        const val CATEGORY_ID = "category_id"
        const val MAKAL_TEXT = "makal_text"
    }
    //////////////////////////////////////////////////////////

}