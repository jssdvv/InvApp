package com.example.inv_app.presentation.features.search

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.inv_app.R
import com.example.inv_app.data.model.barcode_analyzer.MLKitBarcodeAnalyzer
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ScannerScreen(
    navController: NavHostController,
    drawerState: DrawerState,
    isGesturesEnabled: MutableStateFlow<Boolean>
) {
    val viewModel = hiltViewModel<ScannerViewModel>()
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val hasFlash =
        LocalContext.current.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

    val scannedBarcode = viewModel.detectedBarcode.collectAsState()
    val isTorchEnabled = viewModel.isTorchEnabled.collectAsState()
    val zoomRatio = viewModel.zoomRatio.collectAsState()
    val scannedSound = MediaPlayer.create(LocalContext.current, R.raw.scanned_barcode)

    SearchScaffold(
        navController = navController,
        drawerState = drawerState,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AndroidView(
                factory = { context ->
                    viewModel.getCameraPreviewView(
                        lifecycleOwner,
                        MLKitBarcodeAnalyzer { barcode ->
                            barcode.rawValue?.let { scannedBarcode ->
                                viewModel.changeBarcodeValue(scannedBarcode)
                                scannedSound.start()
                            }
                        },
                        context
                    )
                },
                modifier = Modifier
                    .fillMaxSize(),
                update = { previewView ->
                    previewView.controller?.let { cameraController ->
                        if (hasFlash) {
                            cameraController.enableTorch(isTorchEnabled.value)
                        } else {
                            Toast.makeText(
                                previewView.context,
                                previewView.context.getString(R.string.flash_available),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            )
            IconButton(
                onClick = { viewModel.toggleTorch() },
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isTorchEnabled.value) {
                            R.drawable.filled_lightbulb_icon
                        } else {
                            R.drawable.outlined_lightbulb_icon
                        }
                    ),
                    contentDescription = "Flashlight",
                    tint = if (isTorchEnabled.value) {
                        Color(0xFFffcc00)
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
    }
}