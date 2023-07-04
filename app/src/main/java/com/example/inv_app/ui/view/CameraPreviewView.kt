package com.example.inv_app.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.inv_app.data.model.BarcodeAnalyzer
import java.util.concurrent.Executors

@Composable
fun CameraPreviewScreen(
) {
    AndroidView(
        factory = { startCamera(it) },
        modifier = Modifier
            .width(640.dp)
            .height(480.dp)
    )
}

@SuppressLint("RestrictedApi")
private fun startCamera(context: Context): View {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

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

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

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