package com.example.inv_app.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "element_table")
data class ElementEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "invNumber") val invNumber: String,
    @ColumnInfo(name = "description") val description: String
)