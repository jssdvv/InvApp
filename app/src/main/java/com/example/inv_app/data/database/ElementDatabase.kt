package com.example.inv_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inv_app.data.database.dao.ElementDao
import com.example.inv_app.data.database.entities.ElementEntity

@Database(entities = [ElementEntity::class], version = 1)
abstract class ElementDatabase : RoomDatabase() {
    abstract fun getElementDao(): ElementDao
}