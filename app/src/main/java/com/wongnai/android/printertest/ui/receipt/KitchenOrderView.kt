package com.wongnai.android.printertest.ui.receipt

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.wongnai.android.printertest.R
import kotlinx.android.synthetic.main.view_kitchen_order.view.*

class KitchenOrderView : FrameLayout {
	constructor(context: Context) : super(context)
	constructor(
		context: Context,
		attrs: AttributeSet?
	) : super(context, attrs)

	constructor(
		context: Context,
		attrs: AttributeSet?,
		defStyleAttr: Int
	) : super(context, attrs, defStyleAttr)

	constructor(
		context: Context,
		attrs: AttributeSet?,
		defStyleAttr: Int,
		defStyleRes: Int
	) : super(context, attrs, defStyleAttr, defStyleRes)

	init {
		View.inflate(context, R.layout.view_kitchen_order, this)
	}

	fun fillData(
		data: UiPrinterKitchenOrder
	) {
		fillDetailOrder(data)
		fillOrderItem(data.uiBillItems)
	}

	private fun fillDetailOrder(data: UiPrinterKitchenOrder) {
		staffNameTextView.text = resources.getString(R.string.printer_staff_title, data.staffName)
		timeTextView.text = resources.getString(R.string.printer_time_title, data.time)
		tableTextView.text = resources.getString(R.string.printer_table_title, data.tableName)
	}

	private fun fillOrderItem(
		uiBillItems: List<UiPrinterKitchenOrder.BillItem>
	) {
		orderBillItemLayout.removeAllViews()
		uiBillItems.forEach { item ->
			val view =
				KitchenBillItemView(
					context
				)
			orderBillItemLayout.addView(view)
			view.fillData(item)
		}
	}
}
