package com.wongnai.android.printertest

import android.app.Application
import com.wongnai.android.printertest.di.CoreComponent
import com.wongnai.android.printertest.printer.SdkPrinterComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PrinterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PrinterApplication)
        }
        CoreComponent.init()
        SdkPrinterComponent.init()
    }
}
