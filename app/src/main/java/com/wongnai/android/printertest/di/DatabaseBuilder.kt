package com.wongnai.android.printertest.di

import android.content.Context
import androidx.room.Room
import com.wongnai.android.printertest.db.RoomDatabaseStorage

class DatabaseBuilder(private val context: Context) {
	fun buildRoomDatabaseStorage(): RoomDatabaseStorage {
		return Room.databaseBuilder(context, RoomDatabaseStorage::class.java, "room-database-storage")
			.build()
	}
}
