package com.wongnai.android.printertest.ui.receipt

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import com.wongnai.android.printertest.R
import kotlinx.android.synthetic.main.view_kitchen_bill_item.view.*

internal class KitchenBillItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_kitchen_bill_item, this)
    }

    fun fillData(item: UiPrinterKitchenOrder.BillItem) {
        billItemAmountTextView.text = item.amount
        billItemNameTextView.text = item.name

        billItemOptionLayout.removeAllViews()
        item.option.forEach { option ->
            val view = AppCompatTextView(context)
            val attrs = intArrayOf(R.attr.textAppearanceBody1)
            val typedArray = context.obtainStyledAttributes(attrs)
            val resId = typedArray.getResourceId(0, 0)
            typedArray.recycle()
            view.setTextAppearance(resId)
            billItemOptionLayout.addView(view)
            view.text = option
        }

        if (item.note != null) {
            billItemNoteTextView.visibility = View.VISIBLE
            billItemNoteTextView.text = item.note
        } else {
            billItemAmountTextView.visibility = View.GONE
        }
    }
}
