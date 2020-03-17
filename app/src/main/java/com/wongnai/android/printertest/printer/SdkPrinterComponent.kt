package com.wongnai.android.printertest.printer

import org.koin.core.context.loadKoinModules

object SdkPrinterComponent {
	fun init() = loadKoinModules(sdkPrinterModule)
}
