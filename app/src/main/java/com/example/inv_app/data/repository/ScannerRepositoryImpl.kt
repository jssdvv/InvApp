package com.example.inv_app.data.repository

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.inv_app.domain.repository.ScannerRepository
import java.util.concurrent.Executor
import javax.inject.Inject

class ScannerRepositoryImpl @Inject constructor(
    private val processCameraProvider: ProcessCameraProvider,
    private val preview: Preview,
    private val imageAnalysis: ImageAnalysis,
    private val cameraSelector: CameraSelector
) : ScannerRepository {

    override suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        executor: Executor,
        analyzer: ImageAnalysis.Analyzer,
        torchState: Boolean
    ) {
        imageAnalysis.setAnalyzer(
            executor,
            analyzer
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
        try {
            processCameraProvider.unbindAll()
            processCameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageAnalysis
            ).apply {
                cameraControl.enableTorch(torchState)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        preview
    }
}