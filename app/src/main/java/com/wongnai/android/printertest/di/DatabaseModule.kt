package com.wongnai.android.printertest.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
	single { DatabaseBuilder(androidApplication()).buildRoomDatabaseStorage() }
	single { DaoBuilder(get()).getPrinterDetailDao() }
}
