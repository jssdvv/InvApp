package com.example.inv_app.domain.repository

import android.content.Context
import androidx.camera.core.ImageAnalysis
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraRepository {
    fun getLifecycleCameraController(
        lifecycleOwner: LifecycleOwner, analyzer: ImageAnalysis.Analyzer, context: Context
    ): LifecycleCameraController

    fun getPreviewView(context: Context): PreviewView
}