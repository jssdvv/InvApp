package com.example.inv_app.domain.repository

import android.view.View
import androidx.camera.core.ImageAnalysis
import androidx.lifecycle.LifecycleOwner

interface ScannerRepository {

    fun showCameraPreview(
        lifecycleOwner: LifecycleOwner,
        analyzer: ImageAnalysis.Analyzer
    ): View
}