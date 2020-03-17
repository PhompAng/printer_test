package com.wongnai.android.printertest.ui.receipt

data class UiPrinterReceipt(
	val businessName: String,
	val branchName: String,
	val address: String,
	val phone: String,
	val billLogoUrl: String?,
	val tableName: String,
	val staffName: String,
	val guests: String,
	val receiptId: String,
	val openBillDate: String,
	val openBillTime: String?,
	val uiBillItems: List<BillItem>,
	val subTotalPrice: String,
	val serviceChargePercent: String?,
	val beforeServiceChargePrice: String,
	val serviceChargePrice: String?,
	val vatPercent: String?,
	val beforeVatPrice: String,
	val vatPrice: String?,
	val totalPrice: String,
	val paymentType: PaymentType
) {
	data class BillItem(
		val name: String,
		val amount: String,
		val note: String?,
		val option: List<String>,
		val price: String
	)

	sealed class PaymentType(
		open val paymentAmount: String
	) {
		data class Cash(
			override val paymentAmount: String,
			val changeAmount: String
		) : PaymentType(paymentAmount)

		data class Credit(
			override val paymentAmount: String,
			val typeCard: String
		) : PaymentType(paymentAmount)
	}
}
