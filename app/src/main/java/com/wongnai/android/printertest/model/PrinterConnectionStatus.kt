package com.wongnai.android.printertest.model

enum class PrinterConnectionStatus(val value: Int) {
	CONNECTED(0),
	OFFLINE(1),
	NOT_CONNECT(2);

	companion object {
		private val map = values().associateBy(PrinterConnectionStatus::value)
		fun create(type: Int) = map[type]
	}
}
