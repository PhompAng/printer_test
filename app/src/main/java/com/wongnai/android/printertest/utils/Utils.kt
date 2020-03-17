package com.wongnai.android.printertest.utils

import android.graphics.Bitmap

object Utils {
    fun getPixelsByBitmap(bitmap: Bitmap): IntArray {
        val width = bitmap.width
        val height = bitmap.height
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
//        bitmap.recycle()
        return pixels
    }

    fun getBMPImageFileByte(bmpBytes: IntArray, width: Int, height: Int): ByteArray {
        var width = width
        var bitWidth = width / 8
        if (7.let { width = width and it; width } > 0) {
            ++bitWidth
        }
        val mBitBytes = ByteArray(bitWidth * height)
        var mBitIndex = 0
        var index = 0
        for (h in 0 until height) {
            var colorValue: Int
            var w: Int
            w = 0
            while (w < bitWidth - 1) {
                colorValue = 0
                if (bmpBytes[index++] < -1) {
                    colorValue += 128
                }
                if (bmpBytes[index++] < -1) {
                    colorValue += 64
                }
                if (bmpBytes[index++] < -1) {
                    colorValue += 32
                }
                if (bmpBytes[index++] < -1) {
                    colorValue += 16
                }
                if (bmpBytes[index++] < -1) {
                    colorValue += 8
                }
                if (bmpBytes[index++] < -1) {
                    colorValue += 4
                }
                if (bmpBytes[index++] < -1) {
                    colorValue += 2
                }
                if (bmpBytes[index++] < -1) {
                    ++colorValue
                }
                mBitBytes[mBitIndex++] = colorValue.toByte()
                ++w
            }
            colorValue = 0
            if (width == 0) {
                w = 8
                while (w > width) {
                    if (bmpBytes[index++] < -1) {
                        colorValue += 1 shl w
                    }
                    --w
                }
            } else {
                w = 0
                while (w < width) {
                    if (bmpBytes[index++] < -1) {
                        colorValue += 1 shl 8 - w
                    }
                    ++w
                }
            }
            mBitBytes[mBitIndex++] = colorValue.toByte()
        }
        return mBitBytes
    }
}
