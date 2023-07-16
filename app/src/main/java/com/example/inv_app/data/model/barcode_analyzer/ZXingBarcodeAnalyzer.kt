package com.example.inv_app.data.model.barcode_analyzer

import android.graphics.ImageFormat
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
//Fix this analyzer
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

        return if (rotationDegrees != 0) {
            rotatedByteArray(
                byteArray,
                rotationDegrees,
                image.height,
                image.width
            )
        } else {
            byteArray
        }
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

    override fun analyze(imageProxy: ImageProxy) {

        if (imageProxy.format in imageFormats) {

            val byteArray = imageToByteArray(imageProxy)
            var height = 0
            var width = 0

            if (imageProxy.imageInfo.rotationDegrees != 180 && imageProxy.imageInfo.rotationDegrees != 0) {
                height = imageProxy.width
                width = imageProxy.height
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
                }.decodeWithState(binaryBitmap)
                onBarcodeScanned(result.text)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                imageProxy.close()
            }
            imageProxy.close()
        }
    }
}