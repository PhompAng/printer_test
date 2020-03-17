package com.wongnai.android.printertest.model.mapper

import com.wongnai.android.printertest.db.model.PrinterEntity
import com.wongnai.android.printertest.model.Printer
import com.wongnai.android.printertest.model.PrinterConnectionStatus
import com.wongnai.android.printertest.model.PrinterConnectionType

object PrinterEntityToPrinterMapper {
    fun map(from: PrinterEntity): Printer {
        return Printer(
            from.portName,
            from.modelName,
            from.identifier,
            PrinterConnectionType.create(from.connectionType)!!,
            from.brand,
            PrinterConnectionStatus.OFFLINE
        )
    }
}
