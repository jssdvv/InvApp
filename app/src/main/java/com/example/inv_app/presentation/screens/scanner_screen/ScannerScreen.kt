package com.example.inv_app.presentation.screens.scanner_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.inv_app.data.model.barcode_analyzer.MLKitBarcodeAnalyzer

@Composable
fun ScannerScreen(
    viewModel: ScannerViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CameraPreview(viewModel)
    }
}

@Composable
fun CameraPreview(viewModel: ScannerViewModel) {
    val scannedBarcode = viewModel.detectedBarcode.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        modifier = Modifier
            .fillMaxSize(),
        factory = {
            viewModel.showCameraPreview(
                lifecycleOwner,
                MLKitBarcodeAnalyzer { barcode ->
                    barcode.rawValue?.let { scannedBarcode ->
                        Log.d("Barcode", scannedBarcode)
                        viewModel.detectBarcode(scannedBarcode)
                    }
                }
            )
        }
    )
    Text(
        text = scannedBarcode.value,
        modifier = Modifier.background(Color.Red)
    )

}