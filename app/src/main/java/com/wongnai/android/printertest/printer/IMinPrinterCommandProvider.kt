package com.wongnai.android.printertest.printer

import android.content.Context
import android.graphics.Bitmap
import com.neostra.neostraprinterlib.port.UsbPrinter
import com.neostra.neostraprinterlib.print.PrintUtils
import com.wongnai.android.printertest.model.Printer
import com.wongnai.android.printertest.model.PrinterConnectionStatus
import com.wongnai.android.printertest.model.PrinterConnectionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IMinPrinterCommandProvider(context: Context) : PrinterCommandProvider {

    private val usbPrinter = UsbPrinter(context)

    override suspend fun find(): List<Printer> {
        withContext(Dispatchers.Main) {
            usbPrinter.initPrinter()
        }
        return listOf(
            Printer(
                "iMin",
                "IP1000 Printer USB001",
                "USB:SN:MS43210",
                PrinterConnectionType.USB,
                "iMin",
                PrinterConnectionStatus.NOT_CONNECT
            )
        )
    }

    override suspend fun print(
        bitmaps: List<Bitmap>,
        portName: String
    ) {
        bitmaps.forEach { bitmap ->
            PrintUtils.printBitImage(usbPrinter, bitmap)
            PrintUtils.partialCut(usbPrinter)
        }
    }
}
