package com.example.inv_app.data.model

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import android.graphics.Color

@Suppress("UNREACHABLE_CODE")
class ZxingBarcode128Writer() {

    private val multiFormatWriter = MultiFormatWriter()

    private fun getBitMatrixPixelColor(bitMatrix: BitMatrix, x: Int, y: Int): Int {
        return if (bitMatrix.get(x, y)) {
            Color.BLACK
        } else {
            Color.WHITE
        }
    }

    fun generateBarcodeBitmap(barcode: String): Bitmap? {
        try {
            val bitMatrix = multiFormatWriter.encode(
                barcode,
                BarcodeFormat.CODE_128,
                46,
                17
            )

            val bitmap = Bitmap.createBitmap(
                    bitMatrix.width,
                    bitMatrix.height,
                    Bitmap.Config.RGB_565
                )

            for (x in 0 until bitMatrix.width) {
                for (y in 0 until bitMatrix.height) {
                    bitmap.setPixel(x, y, getBitMatrixPixelColor(bitMatrix, x, y))
                }
            }
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return null
        }
    }
}