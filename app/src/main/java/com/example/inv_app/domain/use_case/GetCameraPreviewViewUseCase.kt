package com.example.inv_app.domain.use_case

import android.content.Context
import androidx.camera.core.ImageAnalysis
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.inv_app.domain.repository.CameraRepository
import javax.inject.Inject

class GetCameraPreviewViewUseCase @Inject constructor(
    private val repository: CameraRepository
) {
    operator fun invoke(
        lifecycleOwner: LifecycleOwner,
        analyzer: ImageAnalysis.Analyzer,
        context: Context
    ): PreviewView {
        return repository.getPreviewView(context).apply {
            controller = repository.getLifecycleCameraController(
                lifecycleOwner,
                analyzer,
                context
            )
        }
    }
}