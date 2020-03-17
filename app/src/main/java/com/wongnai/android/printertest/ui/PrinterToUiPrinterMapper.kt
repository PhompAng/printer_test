package com.wongnai.android.printertest.ui

import com.wongnai.android.printertest.model.Printer
import com.wongnai.android.printertest.ui.printer.UiPrinter

object PrinterToUiPrinterMapper {
    fun map(from: Printer): UiPrinter {
        return UiPrinter(
            from.portName,
            from.modelName,
            from.portName,
            from.status
        )
    }
}
