package com.wongnai.android.printertest.ui.printer

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class PrinterAdapter(private val onPrinterClickListener: PrinterViewHolder.OnPrinterClickListener) : ListAdapter<UiPrinter, PrinterViewHolder>(
    UiPrinterDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrinterViewHolder {
        return PrinterViewHolder.create(parent, onPrinterClickListener)
    }

    override fun onBindViewHolder(holder: PrinterViewHolder, position: Int) {
        holder.fillData(getItem(position))
    }
}
