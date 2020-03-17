package com.wongnai.android.printertest.db

import androidx.room.*
import com.wongnai.android.printertest.db.model.PrinterEntity
import com.wongnai.android.printertest.db.model.PrinterId
import kotlinx.coroutines.flow.Flow

@Dao
interface PrinterDetailDao {
	@Query("SELECT * from printer_item")
	fun observePrinterItems(): Flow<List<PrinterEntity>>

	@Query("SELECT * from printer_item")
	suspend fun findAll(): List<PrinterEntity>

	@Query("SELECT * from printer_item WHERE port_name = :id")
	suspend fun findById(id: PrinterId): PrinterEntity?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addItems(items: List<PrinterEntity>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addItem(item: PrinterEntity)

	@Update
	suspend fun update(item: PrinterEntity)

	@Query("DELETE FROM printer_item WHERE port_name = :id")
	suspend fun deleteById(id: PrinterId)

	@Query("DELETE FROM printer_item")
	suspend fun clearAll()
}
