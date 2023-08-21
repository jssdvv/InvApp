package com.example.inv_app.presentation.features.search

import android.content.Context
import androidx.camera.core.ImageAnalysis
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.inv_app.domain.use_case.GetCameraPreviewViewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val getCameraPreviewViewUseCase: GetCameraPreviewViewUseCase
) : ViewModel() {
    private val _detectedBarcode = MutableStateFlow("")
    val detectedBarcode = _detectedBarcode.asStateFlow()

    private val _isTorchEnabled = MutableStateFlow(false)
    val isTorchEnabled = _isTorchEnabled.asStateFlow()

    private val _zoomRatio = MutableStateFlow(0f)
    val zoomRatio = _zoomRatio.asStateFlow()

    fun changeBarcodeValue(detectedBarcode: String) {
        _detectedBarcode.value = detectedBarcode
    }

    fun toggleTorch() {
        _isTorchEnabled.value = !_isTorchEnabled.value
    }

    fun getCameraPreviewView(
        lifecycleOwner: LifecycleOwner,
        analyzer: ImageAnalysis.Analyzer,
        context: Context
    ): PreviewView {
        return getCameraPreviewViewUseCase(
            lifecycleOwner = lifecycleOwner,
            analyzer = analyzer,
            context = context
        )
    }
}