package com.wongnai.android.printertest.model

enum class PrinterConnectionType(val value: Int) {
	USB(0),
	LAN(1),
	BLUETOOTH(2);

	companion object {
		private val map = values().associateBy(PrinterConnectionType::value)
		fun create(type: Int) = map[type]
	}
}
