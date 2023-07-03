package com.example.inv_app.data.database.dao

import androidx.room.*
import com.example.inv_app.data.database.entities.ElementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ElementDao {
    @Upsert
    suspend fun upsertElement(entity: ElementEntity)

    @Delete
    suspend fun deleteElement(entity: ElementEntity)

    @Query("SELECT * FROM element_table ORDER BY id ASC")
    fun getElementsOrderedById(): Flow<List<ElementEntity>>

    @Query("SELECT * FROM element_table ORDER BY invNumber ASC")
    fun getElementsOrderedByInvNumber(): Flow<List<ElementEntity>>
}