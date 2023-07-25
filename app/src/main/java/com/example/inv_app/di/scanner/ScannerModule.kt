package com.example.inv_app.di.scanner

import android.content.Context
import android.util.Size
import androidx.camera.core.*
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScannerModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Singleton
    @Provides
    fun provideCameraProvider(
        cameraProviderFuture: MutableStateFlow<ListenableFuture<ProcessCameraProvider>>
    ): ProcessCameraProvider {
        return cameraProviderFuture.value.get()
    }

    @Singleton
    @Provides
    fun provideCameraProviderFuture(
        processCameraProvider: ListenableFuture<ProcessCameraProvider>
    ): MutableStateFlow<ListenableFuture<ProcessCameraProvider>> {
        return MutableStateFlow(processCameraProvider)
    }

    @Singleton
    @Provides
    fun provideProcessCameraProvider(
        context: Context
    ): ListenableFuture<ProcessCameraProvider> {
        return ProcessCameraProvider.getInstance(context)
    }

    @Singleton
    @Provides
    fun providePreview(): Preview {
        return Preview.Builder().build()
    }

    @Singleton
    @Provides
    fun providePreviewView(context: Context): PreviewView {
        return PreviewView(context)
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

    @Singleton
    @Provides
    fun provideExecutor(context: Context): Executor {
        return ContextCompat.getMainExecutor(context)
    }
}