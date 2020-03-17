package com.wongnai.android.printertest.model.mapper

import com.starmicronics.stario.PortInfo
import com.wongnai.android.printertest.model.Printer
import com.wongnai.android.printertest.model.PrinterConnectionStatus
import com.wongnai.android.printertest.model.PrinterConnectionType
import java.util.*

internal object StarPortInfoToPrinterMapper {
    fun map(from: PortInfo): Printer {
        return Printer(
            from.portName,
            from.modelName,
            from.usbSerialNumber,
            PrinterConnectionType.USB,
            "Star",
            PrinterConnectionStatus.NOT_CONNECT
        )
    }
}
