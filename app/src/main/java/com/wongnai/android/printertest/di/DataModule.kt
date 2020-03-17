package com.wongnai.android.printertest.di

import com.wongnai.android.printertest.data.PrinterRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single { PrinterRepository(get(named("star-command")), get(named("imin-command")), get(), get()) }
}
