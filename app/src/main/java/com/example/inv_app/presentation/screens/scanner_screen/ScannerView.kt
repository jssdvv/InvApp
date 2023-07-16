package com.example.inv_app.presentation.screens.scanner_screen

import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.inv_app.R
import com.example.inv_app.data.model.barcode_analyzer.MLKitBarcodeAnalyzer
import java.util.concurrent.Executors

@Composable
fun ScannerView(
) {
    val viewModel: ScannerViewModel = hiltViewModel()
    val detectedBarcode by viewModel.detectedBarcode.collectAsState()

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

            CameraPreview(viewModel = viewModel)

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
                viewModel.toggleTorchState()
            },
            modifier = Modifier.background(Color.Red)
        ) {
            Text(text = "Flashlight")
        }
    }

}

@Composable
fun CameraPreview(
    viewModel: ScannerViewModel
) {
    val torchState by viewModel.torchState.collectAsState()

    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = Executors.newSingleThreadExecutor()
    val executor = ContextCompat.getMainExecutor(context)

    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    val cameraProvider = cameraProviderFuture.get()

    AndroidView(
        factory = {
            val previewView = PreviewView(it)
            viewModel.showCameraPreview(
                previewView,
                lifeCycleOwner,
                executor,
                MLKitBarcodeAnalyzer{barcode ->
                    viewModel.detectBarcode(barcode)
                }
            )
            previewView
        },
        update = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}