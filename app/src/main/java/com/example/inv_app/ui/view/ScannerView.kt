package com.example.inv_app.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Size
import android.view.View
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.inv_app.data.model.BarcodeAnalyzer
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

                        val imageAnalysis = ImageAnalysis.Builder()
                            .setTargetResolution(
                                Size(
                                    previewView.width,
                                    previewView.height
                                )
                            )
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build()

                        imageAnalysis.setAnalyzer(
                            cameraExecutor,
                            BarcodeAnalyzer { barcodes ->
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
                .aspectRatio(1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = androidx.compose.ui.graphics.Color.Red)
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
                )
            }
        }
    }
}

/*

@SuppressLint("RestrictedApi")
private fun startCamera(context: Context): View {
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }

    val previewView = PreviewView(context).also {
        it.scaleType = PreviewView.ScaleType.FILL_CENTER
    }

    cameraProviderFuture.addListener(
        {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val cameraExecutor = Executors.newSingleThreadExecutor()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            val imageCapture = ImageCapture
                .Builder()
                .build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .setResolutionSelector(
                    ResolutionSelector.Builder()
                        .build()
                )
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setImageQueueDepth(1)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, BarcodeAnalyzer(context))
                }

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            try {
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    context as ComponentActivity, cameraSelector, preview, imageCapture, imageAnalyzer
                )
            } catch (e: Exception) {
                Log.e("DEBUG", "Use case binding failed", e)
            }
        },
        ContextCompat.getMainExecutor(context)
    )
    return previewView
}
*/