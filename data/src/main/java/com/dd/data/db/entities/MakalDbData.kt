package com.dd.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = MakalDbData.TABLE_NAME)
data class MakalDbData(
        @PrimaryKey
        @ColumnInfo(name = MAKAL_ID) val makal_id: Int,
        @ColumnInfo(name = MAKAL_TEXT) val makal_text: String
) {

    //////////////////////////TABLE///////////////////////////

    companion object {
        const val TABLE_NAME = "makals"
        const val MAKAL_ID = "makal_id"
        const val MAKAL_TEXT = "makal_text"
    }
    //////////////////////////////////////////////////////////

}