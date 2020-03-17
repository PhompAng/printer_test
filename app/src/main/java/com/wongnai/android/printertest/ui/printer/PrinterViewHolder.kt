package com.wongnai.android.printertest.ui.printer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wongnai.android.printertest.R
import com.wongnai.android.printertest.db.model.PrinterId
import com.wongnai.android.printertest.model.PrinterConnectionStatus
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_item_printer.*

class PrinterViewHolder private constructor(
    override val containerView: View,
    private val onPrinterClickListener: OnPrinterClickListener
) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(
            parent: ViewGroup,
            onPrinterClickListener: OnPrinterClickListener
        ): PrinterViewHolder {
            return PrinterViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_item_printer, parent, false),
                onPrinterClickListener
            )
        }
    }

    private var mData: UiPrinter? = null

    init {
        itemView.setOnClickListener {
            mData?.id?.let {
                onPrinterClickListener.onPrinterClick(it)
            }
        }
    }

    fun fillData(data: UiPrinter) {
        this.mData = data
        modelNameTextView.text = data.modelName
        descriptionTextView.text = data.description
        when (data.status) {
            PrinterConnectionStatus.CONNECTED -> {
                statusTextView.text = "CONNECTED"
                statusTextView.setTextColor(containerView.context.getColor(R.color.green))
            }
            PrinterConnectionStatus.OFFLINE -> {
                statusTextView.text = "OFFLINE"
                statusTextView.setTextColor(containerView.context.getColor(R.color.gray500))
            }
            PrinterConnectionStatus.NOT_CONNECT -> {
                statusTextView.text = "NOT CONNECTED"
                statusTextView.setTextColor(containerView.context.getColor(R.color.gray500))
            }
        }
    }

    interface OnPrinterClickListener {
        fun onPrinterClick(id: PrinterId)
    }
}
