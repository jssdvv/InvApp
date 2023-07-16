package com.example.inv_app.di.scanner

import android.app.Application
import android.util.Size
import android.view.Surface
import androidx.camera.core.*
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScannerModule {

    @Singleton
    @Provides
    fun provideProcessCameraProvider(application: Application): ProcessCameraProvider {
        return ProcessCameraProvider.getInstance(application.applicationContext).get()
    }

    @Singleton
    @Provides
    fun providePreview(): Preview {
        return Preview.Builder().build()
    }

    @Singleton
    @Provides
    fun provideImageAnalysis(): ImageAnalysis {
        val resolutionSelector = ResolutionSelector.Builder()
            .setResolutionStrategy(
                ResolutionStrategy(
                    Size(1000, 1000),
                    ResolutionStrategy.FALLBACK_RULE_CLOSEST_LOWER
                )
            )
            .build()

        return ImageAnalysis.Builder()
            .setResolutionSelector(resolutionSelector)
            .setTargetRotation(Surface.ROTATION_0)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

    @Singleton
    @Provides
    fun provideCameraSelector(): CameraSelector {
        return CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
    }
}