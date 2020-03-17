package com.wongnai.android.printertest.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "printer_item")
data class PrinterEntity(
	@PrimaryKey
	@ColumnInfo(name = "port_name")
	val portName: PrinterId,
	@ColumnInfo(name = "model_name")
	val modelName: String,
	@ColumnInfo(name = "identifier")
	val identifier: String,
	@ColumnInfo(name = "brand")
	val brand: String,
	@ColumnInfo(name = "type")
	val connectionType: Int
)

typealias PrinterId = String
