package com.wongnai.android.printertest.printer

import android.graphics.Bitmap

interface ViewPrinterProvider {
	fun createReceiptImage(): Bitmap
	fun createKitchenReceiptImage(): Bitmap
}
