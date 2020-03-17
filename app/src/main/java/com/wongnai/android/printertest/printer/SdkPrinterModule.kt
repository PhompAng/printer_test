package com.wongnai.android.printertest.printer

import org.koin.core.qualifier.named
import org.koin.dsl.module

val sdkPrinterModule = module {
    single<PrinterCommandProvider>(named("star-command")) { StarPrinterCommandProvider(get()) }
	single<PrinterCommandProvider>(named("imin-command")) { IMinPrinterCommandProvider(get()) }
    single<ViewPrinterProvider> { DefaultViewPrinterProvider(get()) }
}
