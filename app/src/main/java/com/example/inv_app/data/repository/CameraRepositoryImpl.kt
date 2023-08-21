package com.example.inv_app.data.repository

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.inv_app.domain.repository.CameraRepository
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor() : CameraRepository {
    override fun getLifecycleCameraController(
        lifecycleOwner: LifecycleOwner,
        analyzer: ImageAnalysis.Analyzer,
        context: Context
    ): LifecycleCameraController {
        return LifecycleCameraController(context).apply {
            this.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            imageAnalysisTargetSize = CameraController.OutputSize(1)
            imageCaptureFlashMode = ImageCapture.FLASH_MODE_OFF
            imageCaptureMode = ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY
            imageCaptureTargetSize = CameraController.OutputSize(1)
            isPinchToZoomEnabled = false
            previewTargetSize = CameraController.OutputSize(1)
            isTapToFocusEnabled = true
            enableTorch(false)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context),
                analyzer
            )
            bindToLifecycle(lifecycleOwner)
        }
    }

    override fun getPreviewView(context: Context): PreviewView {
        return PreviewView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(0xFF000000.toInt())
            implementationMode = PreviewView.ImplementationMode.PERFORMANCE
            scaleType = PreviewView.ScaleType.FILL_START
            visibility = View.VISIBLE
        }
    }
}