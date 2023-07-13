package com.example.inv_app.ui.view

import android.util.Log
import android.util.Size
import android.view.Surface.ROTATION_0
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.core.resolutionselector.ResolutionStrategy.FALLBACK_RULE_CLOSEST_LOWER
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.inv_app.R
import com.example.inv_app.data.model.barcode_analyzer.MLKitBarcodeAnalyzer
import java.util.concurrent.Executors

@Composable
fun ScannerView(
) {
    var detectedBarcode by remember { mutableStateOf("") }
    var torch by remember { mutableStateOf(false) }
    /*= remember {
        mutableStateListOf<String>()
    }*/

    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = Executors.newSingleThreadExecutor()
    val executor = ContextCompat.getMainExecutor(context)
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    val cameraProvider = cameraProviderFuture.get()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(5.dp))
        ) {
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
                                Size(1000, 1000),
                                FALLBACK_RULE_CLOSEST_LOWER
                            )
                            val resolutionSelector = ResolutionSelector.Builder()
                                .setResolutionStrategy(resolutionStrategy)
                                .build()

                            val imageAnalysis = ImageAnalysis.Builder()
                                .setResolutionSelector(resolutionSelector)
                                .setTargetRotation(ROTATION_0)
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build()

                            val imageCapture = ImageCapture.Builder()
                                .setFlashMode(ImageCapture.FLASH_MODE_OFF)
                                .build()

                            Log.d("flashlight", torch.toString())

                            imageAnalysis.setAnalyzer(
                                cameraExecutor,
                                MLKitBarcodeAnalyzer { barcode ->
                                    detectedBarcode = barcode

                                    /*detectedBarcodes.clear()

                                    detectedBarcodes.add(barcodes)*/
                                }
                            )
                            try {
                                cameraProvider.unbindAll()
                                cameraProvider.bindToLifecycle(
                                    lifeCycleOwner,
                                    cameraSelector,
                                    imageAnalysis,
                                    imageCapture,
                                    preview,
                                ).apply {
                                    cameraControl.enableTorch(torch)
                                }
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
                .aspectRatio(1.5f)
                .background(Color.Blue)
        ) {
            val stickerWidth = 46f
            val stickerHeight = 17f
            val aspectRatio = stickerWidth / stickerHeight
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .aspectRatio(aspectRatio)
                    .background(Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_uis),
                        contentDescription = "uis logo",
                        modifier = Modifier.fillMaxHeight()
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        /*val bitmap: Bitmap? =
                            ZxingBarcode128Writer().generateBarcodeBitmap(
                                detectedBarcode
                            )
                        bitmap?.let {
                            Image(bitmap = it,
                            contentDescription = null)
                        }*/
                        Text(
                            text = detectedBarcode,
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
        Button(
            onClick = {
                torch = !torch
            },
            modifier = Modifier.background(Color.Red)
        ) {
            Text(text = "Flashlight")
        }
    }

}