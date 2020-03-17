package com.wongnai.android.printertest.ui.receipt

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import com.wongnai.android.printertest.R
import kotlinx.android.synthetic.main.view_printer_receipt_bill_item.view.*

internal class PrinterReceiptBillItemView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	init {
		View.inflate(context, R.layout.view_printer_receipt_bill_item, this)
	}

	fun fillData(data: UiPrinterReceipt.BillItem) {
		amountTextView.text = data.amount
		nameTextView.text = data.name
		priceTextView.text = data.price

		receiptOrderOptionLayout.removeAllViews()
		data.option.forEach { option ->
			val view = AppCompatTextView(context)
			val attrs = intArrayOf(R.attr.textAppearanceBody2)
			val typedArray = context.obtainStyledAttributes(attrs)
			val resId = typedArray.getResourceId(0, 0)
			typedArray.recycle()
			view.setTextAppearance(resId)
			receiptOrderOptionLayout.addView(view)
			view.text = option
		}

		if (data.note != null) {
			billItemNoteTextView.visibility = View.VISIBLE
			billItemNoteTextView.text = data.note
		} else {
			billItemNoteTextView.visibility = View.GONE
		}
	}
}
