package com.example.inv_app.data.repository

import android.view.View
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.inv_app.domain.repository.ScannerRepository
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.Executor
import javax.inject.Inject

class ScannerRepositoryImpl @Inject constructor(
    private val cameraProviderFuture: MutableStateFlow<ListenableFuture<ProcessCameraProvider>>,
    private val cameraProvider: ProcessCameraProvider,
    private val previewView: PreviewView,
    private val executor: Executor,
    private val cameraSelector: CameraSelector,
    private val imageAnalysis: ImageAnalysis,
    private val preview: Preview
) : ScannerRepository {

    override fun showCameraPreview(
        lifecycleOwner: LifecycleOwner,
        analyzer: ImageAnalysis.Analyzer,
    ): View {
        cameraProviderFuture.value.addListener(
            {
                imageAnalysis.setAnalyzer(
                    executor,
                    analyzer
                )
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        imageAnalysis,
                        preview
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            executor
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
        return previewView
    }
}