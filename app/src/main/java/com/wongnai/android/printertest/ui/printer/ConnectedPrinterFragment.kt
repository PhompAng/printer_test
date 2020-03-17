package com.wongnai.android.printertest.ui.printer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import com.wongnai.android.printertest.R
import com.wongnai.android.printertest.db.model.PrinterId
import com.wongnai.android.printertest.ui.printer.add.AddPrinterActivity
import kotlinx.android.synthetic.main.fragment_connected_printer.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConnectedPrinterFragment : Fragment(R.layout.fragment_connected_printer), PrinterViewHolder.OnPrinterClickListener {
    private val mViewModel: PrintersViewModel by sharedViewModel()
    private lateinit var mAdapter: PrinterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = PrinterAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printersRecyclerView.adapter = mAdapter
        printersRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        toolbar.setOnMenuItemClickListener {
            startActivity(Intent(requireContext(), AddPrinterActivity::class.java))
            true
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.connectedPrinters.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }

    override fun onPrinterClick(id: PrinterId) {
        AlertDialog.Builder(requireContext()).setTitle("Add this printer ?")
            .setPositiveButton("Ok") { dialog, _ ->
                mViewModel.removePrinter(id)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
