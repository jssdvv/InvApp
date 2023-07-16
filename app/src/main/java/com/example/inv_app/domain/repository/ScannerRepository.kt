package com.example.inv_app.domain.repository

import androidx.camera.core.ImageAnalysis
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executor

interface ScannerRepository {

    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        executor: Executor,
        analyzer: ImageAnalysis.Analyzer,
        torchState: Boolean
    )
}