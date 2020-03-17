package com.wongnai.android.printertest.data

import com.wongnai.android.printertest.db.PrinterDetailDao
import com.wongnai.android.printertest.db.model.PrinterId
import com.wongnai.android.printertest.model.Printer
import com.wongnai.android.printertest.model.PrinterConnectionStatus
import com.wongnai.android.printertest.model.mapper.PrinterEntityToPrinterMapper
import com.wongnai.android.printertest.model.mapper.PrinterToPrinterEntityMapper
import com.wongnai.android.printertest.printer.PrinterCommandProvider
import com.wongnai.android.printertest.printer.ViewPrinterProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import timber.log.Timber

class PrinterRepository(
    private val starPrinterCommandProvider: PrinterCommandProvider,
    private val iMinPrinterCommandProvider: PrinterCommandProvider,
    private val printerDetailDao: PrinterDetailDao,
    private val viewPrinterProvider: ViewPrinterProvider
) {
    suspend fun getNotConnectedPrinters(): List<Printer> {
        val foundPrinters = getFoundPrinters()
        val connectedPrinters =
            printerDetailDao.findAll().map(PrinterEntityToPrinterMapper::map).map { it.portName }
        return foundPrinters.filter { it.portName !in connectedPrinters }
    }

    suspend fun getConnectedPrinters(): List<Printer> {
        val foundPrinters = getFoundPrinters().map { it.portName }
        return printerDetailDao.findAll().map(PrinterEntityToPrinterMapper::map).map {
            it.copy(status = if (it.portName in foundPrinters) PrinterConnectionStatus.CONNECTED else PrinterConnectionStatus.OFFLINE)
        }
    }

    private suspend fun getFoundPrinters(): List<Printer> {
        return starPrinterCommandProvider.find() + iMinPrinterCommandProvider.find()
    }

    suspend fun savePrinter(id: PrinterId) {
        val foundPrinter = getFoundPrinters().firstOrNull { it.portName == id } ?: return
        printerDetailDao.addItem(PrinterToPrinterEntityMapper.map(foundPrinter))
    }

    suspend fun print() {
        supervisorScope {
            getConnectedPrinters().filter { it.status == PrinterConnectionStatus.CONNECTED }.map {
                async {
                    try {
                        when (it.brand) {
                            "Star" -> {
                                starPrinterCommandProvider.print(
                                    listOf(viewPrinterProvider.createReceiptImage()),
                                    it.portName
                                )
                            }
                            "iMin" -> {
                                iMinPrinterCommandProvider.print(
                                    listOf(viewPrinterProvider.createReceiptImage()),
                                    it.portName
                                )
                            }
                        }
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }.awaitAll()
        }
    }

    suspend fun removePrinter(id: PrinterId) {
        printerDetailDao.deleteById(id)
    }
}
