package com.example.inv_app.di

import android.content.Context
import androidx.room.Room
import com.example.inv_app.data.database.ElementDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ElementModule {

    private const val QUOTE_DATABASE_NAME = "element_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ElementDatabase::class.java, QUOTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideElementDao(db: ElementDatabase) = db.getElementDao()
}