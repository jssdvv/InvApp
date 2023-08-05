package com.example.inv_app.presentation.features.scanner

import android.view.View
import androidx.camera.core.ImageAnalysis
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.inv_app.domain.repository.ScannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val scannerRepository: ScannerRepository
) : ViewModel() {

    private val _detectedBarcode = MutableStateFlow("")
    val detectedBarcode = _detectedBarcode.asStateFlow()

    fun detectBarcode(barcode: String) {
        _detectedBarcode.value = barcode
    }

    fun showCameraPreview(
        lifecycleOwner: LifecycleOwner,
        analyzer: ImageAnalysis.Analyzer
    ): View {
        return scannerRepository.showCameraPreview(
            lifecycleOwner = lifecycleOwner,
            analyzer = analyzer,
        )
    }
}