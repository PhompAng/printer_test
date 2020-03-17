package com.wongnai.android.printertest.ui.receipt

data class UiPrinterKitchenOrder(
	val tableName: String,
	val staffName: String,
	val time: String,
	val uiBillItems: List<BillItem>
) {
	data class BillItem(
		val name: String,
		val amount: String,
		val menuGroupId: Long,
		val note: String?,
		val option: List<String>
	)
}
