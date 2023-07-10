package com.example.inv_app.ui.view

import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.core.resolutionselector.ResolutionStrategy.FALLBACK_RULE_CLOSEST_LOWER
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.inv_app.data.model.MlkitBarcodeAnalyzer
import com.google.mlkit.vision.barcode.common.Barcode
import java.util.concurrent.Executors

@Composable
fun ScannerView(
) {
    val detectedBarcodes = remember {
        mutableStateListOf<Barcode>()
    }
    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = Executors.newSingleThreadExecutor()
    val executor = ContextCompat.getMainExecutor(context)
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    val cameraProvider = cameraProviderFuture.get()
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)) {
            AndroidView(
                factory = {
                    val previewView = PreviewView(it)
                    cameraProviderFuture.addListener(
                        {
                            val preview = Preview.Builder().build()
                            val cameraSelector = CameraSelector.Builder()
                                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                                .build()
                            preview.setSurfaceProvider(previewView.surfaceProvider)

                            val resolutionStrategy = ResolutionStrategy(
                                Size(500, 500),
                                FALLBACK_RULE_CLOSEST_LOWER
                            )
                            val resolutionSelector = ResolutionSelector.Builder()
                                .setResolutionStrategy(resolutionStrategy)
                                .build()

                            val imageAnalysis = ImageAnalysis.Builder()
                                .setResolutionSelector(resolutionSelector)
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build()

                            imageAnalysis.setAnalyzer(
                                cameraExecutor,
                                MlkitBarcodeAnalyzer { barcodes ->
                                    detectedBarcodes.clear()
                                    detectedBarcodes.addAll(barcodes)
                                }
                            )
                            try {
                                cameraProvider.unbindAll()
                                cameraProvider.bindToLifecycle(
                                    lifeCycleOwner,
                                    cameraSelector,
                                    imageAnalysis,
                                    preview,
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            preview
                        },
                        executor
                    )
                    previewView
                },
                modifier = Modifier
                    .fillMaxWidth()
                    //.align(Alignment.Center)
                    .aspectRatio(1f)
            )
            Divider(
                color = Color.Red,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.Blue)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Códigos de barras: ")
                Spacer(modifier = Modifier.padding(15.dp))
                detectedBarcodes.forEach {
                    Text(
                        text = it.rawValue.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Magenta)
                    )
                }
            }
        }
    }
}