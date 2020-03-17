package com.wongnai.android.printertest.utils

import android.graphics.Bitmap
import com.neostra.neostraprinterlib.port.UsbPrinter

object MyPrintUtils {
    fun printBitImage(printer: UsbPrinter, bitmap: Bitmap) {
        val bytes = getBytesByBitmap(bitmap)
        val printBytes = ByteArray(bytes.size + 8)
        var bitWidth = bitmap.width shr 3
        if (bitmap.width and 7 > 0) {
            ++bitWidth
        }
        val height = bitmap.height
        printBytes[0] = 29
        printBytes[1] = 118
        printBytes[2] = 48
        printBytes[3] = 0
        printBytes[4] = (bitWidth and 255).toByte()
        printBytes[5] = (bitWidth shr 8).toByte()
        printBytes[6] = (height and 255).toByte()
        printBytes[7] = (height shr 8).toByte()
        System.arraycopy(bytes, 0, printBytes, 8, bytes.size)
        printer.writePort(printBytes, printBytes.size)
    }

    private fun getBytesByBitmap(bitmap: Bitmap): ByteArray {
        val pixelsBitmap =
            Utils.getPixelsByBitmap(bitmap)
        return Utils.getBMPImageFileByte(
            pixelsBitmap,
            bitmap.width,
            bitmap.height
        )
    }
}
