package com.wongnai.android.printertest.ui.receipt

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.wongnai.android.printertest.R
import kotlinx.android.synthetic.main.view_printer_receipt_payment.view.*

class PrinterReceiptPaymentView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	init {
		View.inflate(context, R.layout.view_printer_receipt_payment, this)
	}

	fun fillData(title: String, price:String){
		titleTextView.text = title
		priceTextView.text = price
	}
}
