package com.wongnai.android.printertest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wongnai.android.printertest.db.model.PrinterEntity

@Database(entities = [PrinterEntity::class],
		  version = 1)
abstract class RoomDatabaseStorage : RoomDatabase() {
	abstract fun printerDetailDao(): PrinterDetailDao
}
