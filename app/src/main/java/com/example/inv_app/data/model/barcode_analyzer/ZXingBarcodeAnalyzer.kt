package com.example.inv_app.data.model.barcode_analyzer

import android.graphics.ImageFormat
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer

class ZXingBarcodeAnalyzer(
    private val onBarcodeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val imageFormats = listOf(
        ImageFormat.YUV_420_888,
        ImageFormat.YUV_422_888,
        ImageFormat.YUV_444_888
    )

    private fun imageToByteArray(image: ImageProxy): ByteArray {
        val rotationDegrees = image.imageInfo.rotationDegrees
        val plane = image.planes.first()
        val byteBuffer = plane.buffer
        val byteArray = ByteArray(byteBuffer.remaining())
        byteBuffer.apply {
            rewind()
            get(byteArray)
        }

        if (rotationDegrees != 0) {
            return rotatedByteArray(
                byteArray,
                rotationDegrees,
                image.height,
                image.width
            )
        }
        return byteArray
    }

    private fun rotatedByteArray(
        imageByteArray: ByteArray,
        rotationDegrees: Int,
        height: Int,
        width: Int
    ): ByteArray {
        val rotatedByteArray = ByteArray(imageByteArray.size)
        return when (rotationDegrees) {
            90 -> {
                for (y in 0 until height) {
                    for (x in 0 until width) {
                        rotatedByteArray[x * height + height - y - 1] =
                            imageByteArray[x + y * width]
                    }
                }
                return rotatedByteArray
            }
            180 -> {
                for (y in 0 until height) {
                    for (x in 0 until width) {
                        rotatedByteArray[width * (height - y - 1) + width - x - 1] =
                            imageByteArray[x + y * width]
                    }
                }
                return rotatedByteArray
            }
            270 -> {
                for (y in 0 until height) {
                    for (x in 0 until width) {
                        rotatedByteArray[y + x * height] = imageByteArray[y * width + width - x - 1]
                    }
                }
                return rotatedByteArray
            }
            else -> imageByteArray
        }
    }

    private val barcodeFormats = mapOf(
        DecodeHintType.POSSIBLE_FORMATS to arrayListOf(
            BarcodeFormat.QR_CODE,
            BarcodeFormat.CODE_128,
            BarcodeFormat.CODE_39
        )
    )


    override fun analyze(image: ImageProxy) {

        if (image.format in imageFormats) {

            val byteArray = imageToByteArray(image)
            var height = 0
            var width = 0

            Log.d("rotación", image.imageInfo.rotationDegrees.toString())

            if (image.imageInfo.rotationDegrees != 180 && image.imageInfo.rotationDegrees != 0) {
                height = image.width
                width = image.height
            }

            val planarYUVLuminanceSource = PlanarYUVLuminanceSource(
                byteArray,
                width,
                height,
                0,
                0,
                width,
                height,
                false
            )
            val binaryBitmap = BinaryBitmap(HybridBinarizer(planarYUVLuminanceSource))

            try {
                val result = MultiFormatReader().apply {
                    setHints(barcodeFormats)
                }.decode(binaryBitmap)
                Log.d("Barcode", result.text)
                onBarcodeScanned(result.text)
            } catch (e: Exception) {
                Log.e("NoCode", "No barcodes detected")
                e.printStackTrace()
            } finally {
                image.close()
            }
        }
    }
}