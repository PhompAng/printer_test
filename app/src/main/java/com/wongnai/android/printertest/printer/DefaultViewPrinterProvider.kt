package com.wongnai.android.printertest.printer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.view.ContextThemeWrapper
import com.wongnai.android.printertest.R
import com.wongnai.android.printertest.ui.receipt.KitchenOrderView
import com.wongnai.android.printertest.ui.receipt.PrinterReceiptView
import com.wongnai.android.printertest.ui.receipt.UiPrinterKitchenOrder
import com.wongnai.android.printertest.ui.receipt.UiPrinterReceipt

internal class DefaultViewPrinterProvider(
    private val context: Context
) : ViewPrinterProvider {

    override fun createReceiptImage(): Bitmap {
        val uiPrinterReceipt = createReceipt()
        val contextThemeWrapper = ContextThemeWrapper(context, R.style.Printer)
        val view: View = LayoutInflater.from(contextThemeWrapper)
            .inflate(R.layout.view_printer, null)

        val receiptView = PrinterReceiptView(contextThemeWrapper)
        val spaceLayout = view.findViewById<LinearLayout>(R.id.framePrinterLayout)

        spaceLayout.removeAllViews()
        spaceLayout.addView(receiptView)

        receiptView.fillData(uiPrinterReceipt)

        return createBitmap(view)
    }

    override fun createKitchenReceiptImage(): Bitmap {
        val uiPrinterKitchenOrder = createKitchenReceipt()
        val contextThemeWrapper = ContextThemeWrapper(context, R.style.Printer)
        val view: View = LayoutInflater.from(contextThemeWrapper)
            .inflate(R.layout.view_printer, null)

        val receiptView = KitchenOrderView(contextThemeWrapper)
        val spaceLayout = view.findViewById<LinearLayout>(R.id.framePrinterLayout)

        spaceLayout?.removeAllViews()
        spaceLayout?.addView(receiptView)

        receiptView.fillData(uiPrinterKitchenOrder)

        return createBitmap(view)
    }

    private fun createReceipt(): UiPrinterReceipt {
        return UiPrinterReceipt(
            "Sendai Ramen Mokkori",
            "Thonglor",
            "ซอยสุขุมวิท 55, คลองตันเหนือ, กรุงเทพมหานคร, 10110",
            "02-392-0811",
            null,
            "Counter",
            "John Doe",
            "1",
            "1",
            "2020-01-01",
            "00:00:00",
            listOf(
                UiPrinterReceipt.BillItem(
                    "ราเม็งชาชูซุปเกลือ",
                    "1",
                    "ไม่ใส่ผัก",
                    listOf("ชาชู, ไข่"),
                    "250.0"
                )
            ),
            "250.0",
            null,
            "250.0",
            null,
            null,
            "250.0",
            null,
            "250.0",
            UiPrinterReceipt.PaymentType.Cash("500.0", "250.0")
        )
    }

    private fun createKitchenReceipt(): UiPrinterKitchenOrder {
        return UiPrinterKitchenOrder(
            "Counter",
            "John Doe",
            "01/02/2020 12:00:00",
            listOf(
                UiPrinterKitchenOrder.BillItem(
                    "ราเม็งชาชูซุปเกลือ",
                    "1",
                    1,
                    "Note",
                    listOf("ชาชู, ไข่")
                )
            )
        )
    }

    private fun createBitmap(view: View): Bitmap {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )

        view.layout(0, 0, view.measuredWidth, view.measuredHeight)

        val bitmap =
            Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}
