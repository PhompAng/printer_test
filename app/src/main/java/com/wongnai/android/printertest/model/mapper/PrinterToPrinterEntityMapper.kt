package com.wongnai.android.printertest.model.mapper

import com.wongnai.android.printertest.db.model.PrinterEntity
import com.wongnai.android.printertest.model.Printer

object PrinterToPrinterEntityMapper {
    fun map(from: Printer): PrinterEntity {
        return PrinterEntity(
            from.portName,
            from.modelName,
            from.identifier,
            from.brand,
            from.connectionType.ordinal
        )
    }
}
