package com.example.inv_app.data.model

import android.content.Context
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer(
    private val context: Context
) : ImageAnalysis.Analyzer {

    override fun analyze(imageProxy: ImageProxy) {

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_CODE_128)
            .build()

        val scanner = BarcodeScanning.getClient(options)
        val mediaImage = imageProxy.image

        mediaImage?.let {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            val result = scanner.process(image)
                .addOnSuccessListener {barcodes ->
                    for (barcode in barcodes) {
                        val rawValue = barcode.rawValue
                        Toast.makeText(context,"Código: ${rawValue}", Toast.LENGTH_SHORT).show()
                        println(rawValue)
                    }
                }
                .addOnFailureListener{

                }
        }
        imageProxy.close()
    }
}