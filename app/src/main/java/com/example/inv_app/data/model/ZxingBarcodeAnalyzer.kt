package com.example.inv_app.data.model

import android.graphics.ImageFormat
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import kotlinx.coroutines.scheduling.DefaultIoScheduler.default

class ZxingBarcodeAnalyzer(
    private val onBarcodeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val imageFormats = listOf(
        ImageFormat.YUV_420_888,
        ImageFormat.YUV_422_888,
        ImageFormat.YUV_444_888
    )

    private fun imageToByteArray(image: ImageProxy): ByteArray {
        val plane = image.planes.first()
        val byteBuffer = plane.buffer
        byteBuffer.get()
        byteBuffer.rewind()
        return ByteArray(byteBuffer.remaining())
    }

    private fun rotateImageByteArray(
        imageByteArray: ByteArray,
        rotationDegrees: Int,
        width: Int,
        height: Int
    ) : ByteArray{
        if (rotationDegrees % 90 != 0) return imageByteArray
        when (rotationDegrees) {
            0 -> return imageByteArray
            90 -> {}
            180 -> return imageByteArray
            270 -> {}
            else -> return imageByteArray
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

            val planarYUVLuminanceSource = PlanarYUVLuminanceSource(
                byteArray,
                image.width,
                image.height,
                0,
                0,
                image.width,
                image.height,
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
                image.close()
            }
        }
    }
}