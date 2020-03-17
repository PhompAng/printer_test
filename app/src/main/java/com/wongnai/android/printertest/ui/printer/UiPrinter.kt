package com.wongnai.android.printertest.ui.printer

import androidx.recyclerview.widget.DiffUtil
import com.wongnai.android.printertest.db.model.PrinterId
import com.wongnai.android.printertest.model.PrinterConnectionStatus

data class UiPrinter(
    val id: PrinterId,
    val modelName: String,
    val description: String,
    val status: PrinterConnectionStatus
)

class UiPrinterDiffCallback : DiffUtil.ItemCallback<UiPrinter>() {
    override fun areItemsTheSame(oldItem: UiPrinter, newItem: UiPrinter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiPrinter, newItem: UiPrinter): Boolean {
        return oldItem == newItem
    }
}
