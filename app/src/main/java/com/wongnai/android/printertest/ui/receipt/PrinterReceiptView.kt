package com.wongnai.android.printertest.ui.receipt

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.wongnai.android.printertest.R
import com.wongnai.android.printertest.ui.receipt.PrinterReceiptBillItemView
import com.wongnai.android.printertest.ui.receipt.PrinterReceiptPaymentView
import com.wongnai.android.printertest.ui.receipt.PrinterReceiptSummaryView
import kotlinx.android.synthetic.main.view_printer_receipt.view.*
import kotlinx.android.synthetic.main.view_printer_receipt.view.staffNameTextView
import kotlinx.android.synthetic.main.view_printer_receipt.view.tableTextView

internal class PrinterReceiptView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	init {
		View.inflate(context, R.layout.view_printer_receipt, this)
	}

	fun fillData(data: UiPrinterReceipt) {
		fillReceiptBusiness(data)
		fillReceiptDetail(data)
		fillReceiptBillItem(data.uiBillItems)
		fillReceiptBillSummary(data)
		fillReceiptPayment(data.paymentType)
	}

	private fun fillReceiptBusiness(data: UiPrinterReceipt) {
		receiptTitleTextView.text = data.businessName
		receiptSubTitleTextView.text = data.branchName
		receiptTelTextView.text = data.phone
	}

	private fun fillReceiptDetail(data: UiPrinterReceipt) {
		tableTextView.text = resources.getString(R.string.printer_table_title, data.tableName)
		staffNameTextView.text = resources.getString(R.string.printer_staff_title, data.staffName)
		guestsTextView.text = resources.getString(R.string.printer_guests_title, data.guests)
		receiptIDTextView.text = resources.getString(R.string.printer_receipt_id_title, data.receiptId)
		dateTextView.text = resources.getString(R.string.printer_date_title, data.openBillDate)
		timeTextView.text = resources.getString(R.string.printer_time_title, data.openBillTime)
	}

	private fun fillReceiptBillItem(uiBillItems: List<UiPrinterReceipt.BillItem>) {
		receiptBillItemLayout.removeAllViews()
		uiBillItems.forEach { item ->
			val view = PrinterReceiptBillItemView(context)
			receiptBillItemLayout.addView(view)
			view.fillData(item)
		}
	}

	private fun fillReceiptBillSummary(data: UiPrinterReceipt) {
		receiptTotalBillItemTextView.text =
			resources.getString(R.string.printer_item_title, data.uiBillItems.size.toString())
		receiptTotalPriceTextView.text = data.totalPrice

		billItemsLayout.removeAllViews()
		val view = PrinterReceiptSummaryView(context)

		billItemsLayout.addView(view)
		view.fillData("Subtotal", data.subTotalPrice)

		data.serviceChargePercent?.let {
			val viewServiceCharge = PrinterReceiptSummaryView(context)
			billItemsLayout.addView(viewServiceCharge)
			viewServiceCharge.fillData("Service Charge(${data.serviceChargePercent}%)", data.serviceChargePrice ?: "")
		}

		data.vatPercent?.let {
			val viewBeforeVat = PrinterReceiptSummaryView(context)
			billItemsLayout.addView(viewBeforeVat)
			viewBeforeVat.fillData("Before VAT", data.beforeVatPrice)

			val viewVat = PrinterReceiptSummaryView(context)
			billItemsLayout.addView(viewVat)
			viewVat.fillData("VAT(${data.vatPercent})", data.vatPrice ?: "")
		}
	}

	private fun fillReceiptPayment(paymentType: UiPrinterReceipt.PaymentType) {
		receiptPaymentLayout.removeAllViews()

		when (paymentType) {
			is UiPrinterReceipt.PaymentType.Cash -> {
				val viewCash = PrinterReceiptPaymentView(context)
				receiptPaymentLayout.addView(viewCash)
				viewCash.fillData("Cash", paymentType.paymentAmount)

				val viewChange = PrinterReceiptPaymentView(context)
				receiptPaymentLayout.addView(viewChange)
				viewChange.fillData("Change", paymentType.changeAmount)
			}
			is UiPrinterReceipt.PaymentType.Credit -> {
				val viewCredit = PrinterReceiptPaymentView(context)
				receiptPaymentLayout.addView(viewCredit)
				viewCredit.fillData(paymentType.typeCard, paymentType.paymentAmount)
			}
		}
	}
}

