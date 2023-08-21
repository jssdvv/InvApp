package com.example.inv_app.di

import com.example.inv_app.data.repository.CameraRepositoryImpl
import com.example.inv_app.domain.repository.CameraRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CameraRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindCameraRepository(repository: CameraRepositoryImpl): CameraRepository
}