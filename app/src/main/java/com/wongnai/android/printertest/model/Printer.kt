package com.wongnai.android.printertest.model

import com.wongnai.android.printertest.db.model.PrinterId

data class Printer(
	val portName: PrinterId,
	val modelName: String,
	val identifier: String,
	val connectionType: PrinterConnectionType,
	val brand: String,
	val status: PrinterConnectionStatus
)
