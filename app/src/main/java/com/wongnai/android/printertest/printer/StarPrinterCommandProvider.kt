package com.wongnai.android.printertest.printer

import android.content.Context
import android.graphics.Bitmap
import com.starmicronics.stario.StarIOPort
import com.starmicronics.stario.StarIOPortException
import com.starmicronics.starioextension.ICommandBuilder
import com.starmicronics.starioextension.StarIoExt
import com.wongnai.android.printertest.model.Printer
import com.wongnai.android.printertest.model.mapper.StarPortInfoToPrinterMapper

class StarPrinterCommandProvider(private val context: Context) : PrinterCommandProvider {

	override suspend fun find(): List<Printer> {
		return try {
			StarIOPort.searchPrinter("USB:", context).filter { it.modelName.contains("Star") }
				.map(StarPortInfoToPrinterMapper::map)
		} catch (ex: StarIOPortException) {
			listOf()
		}
	}

	override suspend fun print(
		bitmaps: List<Bitmap>,
		portName: String
	) {
		try {
			val raster = createView(bitmaps)
			val mPort = StarIOPort.getPort(portName, "", 10000, context)
			mPort.writePort(raster, 0, raster.size)
			StarIOPort.releasePort(mPort)
		} catch (e: StarIOPortException) {
			throw PrintReceiptException(e.errorCode, e.message ?: "Unknown")
		}
	}

	private fun createView(
		bitmaps: List<Bitmap>
	): ByteArray {
		val emulation = StarIoExt.Emulation.StarGraphic
		val builder = StarIoExt.createCommandBuilder(emulation)

		builder.beginDocument()
		bitmaps.forEach {
			builder.appendBitmap(it, true, 576, true)
			builder.appendCutPaper(ICommandBuilder.CutPaperAction.PartialCutWithFeed)
		}
		builder.endDocument()

		return builder.commands
	}
}

class PrintReceiptException(errorCode: Int, message: String) : RuntimeException("Print Receipt Error ($errorCode): $message")
