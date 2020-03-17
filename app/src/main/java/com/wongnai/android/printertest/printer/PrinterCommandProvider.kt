package com.wongnai.android.printertest.printer

import android.graphics.Bitmap
import com.wongnai.android.printertest.model.Printer

interface PrinterCommandProvider {
	suspend fun find(): List<Printer>
	suspend fun print(
		bitmaps: List<Bitmap>,
		portName: String
	)
}
