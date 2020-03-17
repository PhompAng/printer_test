package com.wongnai.android.printertest.ui.printer.add

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import com.wongnai.android.printertest.R
import com.wongnai.android.printertest.db.model.PrinterId
import com.wongnai.android.printertest.ui.printer.PrinterAdapter
import com.wongnai.android.printertest.ui.printer.PrinterViewHolder
import com.wongnai.android.printertest.ui.printer.PrintersViewModel
import kotlinx.android.synthetic.main.activity_add_printer.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPrinterActivity : AppCompatActivity(R.layout.activity_add_printer),
    PrinterViewHolder.OnPrinterClickListener {
    private val mViewModel: PrintersViewModel by viewModel()
    private lateinit var mAdapter: PrinterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = PrinterAdapter(this)

        printersRecyclerView.adapter = mAdapter
        printersRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        mViewModel.notConnectedPrinters.observe(this) {
            mAdapter.submitList(it)
        }
    }

    override fun onPrinterClick(id: PrinterId) {
        AlertDialog.Builder(this).setTitle("Add this printer ?")
            .setPositiveButton("Ok") { dialog, _ ->
                mViewModel.savePrinter(id)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
