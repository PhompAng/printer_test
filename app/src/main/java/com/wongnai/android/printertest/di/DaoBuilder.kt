package com.wongnai.android.printertest.di

import com.wongnai.android.printertest.db.PrinterDetailDao
import com.wongnai.android.printertest.db.RoomDatabaseStorage

class DaoBuilder(private val roomDatabaseStorage: RoomDatabaseStorage) {
	fun getPrinterDetailDao(): PrinterDetailDao {
		return roomDatabaseStorage.printerDetailDao()
	}
}
