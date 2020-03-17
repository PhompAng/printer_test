package com.wongnai.android.printertest.ui.printer

import androidx.lifecycle.*
import com.wongnai.android.printertest.data.PrinterRepository
import com.wongnai.android.printertest.db.model.PrinterId
import com.wongnai.android.printertest.ui.PrinterToUiPrinterMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrintersViewModel(private val printerRepository: PrinterRepository) : ViewModel() {
    private val _notConnectedPrinters = liveData(Dispatchers.IO) {
        while (true) {
            emit(printerRepository.getNotConnectedPrinters())
            delay(5000L)
        }
    }
    private val _connectedPrinters = liveData(Dispatchers.IO) {
        while (true) {
            emit(printerRepository.getConnectedPrinters())
            delay(5000L)
        }
    }
    val notConnectedPrinters: LiveData<List<UiPrinter>> = _notConnectedPrinters.map {
        it.map(PrinterToUiPrinterMapper::map)
    }
    val connectedPrinters: LiveData<List<UiPrinter>> = _connectedPrinters.map {
        it.map(PrinterToUiPrinterMapper::map)
    }

    fun savePrinter(id: PrinterId) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                printerRepository.savePrinter(id)
            }
        }
    }

    fun print() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                printerRepository.print()
            }
        }
    }

    fun removePrinter(id: PrinterId) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                printerRepository.removePrinter(id)
            }
        }
    }
}
