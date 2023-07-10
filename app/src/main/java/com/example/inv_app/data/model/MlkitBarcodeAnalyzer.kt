package com.example.inv_app.data.model

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class MlkitBarcodeAnalyzer(
    private val onBarcodeScanned: (barcodes: MutableList<Barcode>) -> Unit
) : ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_CODE_128,
                Barcode.FORMAT_CODE_39
            )
            .enableAllPotentialBarcodes()
            .build()

        val scanner = BarcodeScanning.getClient(options)

        val mediaImage = imageProxy.image

        mediaImage?.let { imageToAnalyze ->

            val imageToProcess = InputImage.fromMediaImage(
                imageToAnalyze,
                imageProxy.imageInfo.rotationDegrees
            )

            scanner.process(imageToProcess)
                .addOnSuccessListener { barcodes ->

                    barcodes.firstOrNull()

                    if(barcodes.isEmpty()){
                        println("Lista vacía")
                    } else  {
                        onBarcodeScanned(barcodes)
                    }
                }
                .addOnFailureListener {failure ->
                    failure.printStackTrace()
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
        imageProxy.close()
    }
}