package com.example.inv_app.presentation.screens.scanner_screen

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.Analyzer
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inv_app.domain.repository.ScannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val scannerRepository: ScannerRepository
) : ViewModel() {

    private val _torchState = MutableStateFlow(false)
    val torchState = _torchState.asStateFlow()

    private val _detectedBarcode = MutableStateFlow("")
    val detectedBarcode = _detectedBarcode.asStateFlow()

    fun toggleTorchState(){
        _torchState.value = !_torchState.value
    }

    fun detectBarcode(barcode: String) {
        _detectedBarcode.value = barcode
    }

    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        executor: Executor,
        analyzer: ImageAnalysis.Analyzer
        ){
        viewModelScope.launch() {
            scannerRepository.showCameraPreview(
                previewView = previewView,
                lifecycleOwner = lifecycleOwner,
                executor = executor,
                analyzer = analyzer,
                torchState = _torchState.value
            )
        }
    }
}