package com.example.inv_app.di.scanner

import com.example.inv_app.data.repository.ScannerRepositoryImpl
import com.example.inv_app.domain.repository.ScannerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ScannerRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindScannerRepository(
        scannerRepositoryImpl: ScannerRepositoryImpl
    ): ScannerRepository
}