package com.wongnai.android.printertest.ui.receipt

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.wongnai.android.printertest.R
import kotlinx.android.synthetic.main.view_printer_receipt_summary.view.*

class PrinterReceiptSummaryView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	init {
		View.inflate(context, R.layout.view_printer_receipt_summary, this)
	}

	fun fillData(
		title: String,
		price: String
	) {
		typeNameTextView.text = "$title:"
		priceTextView.text = price
	}

}
