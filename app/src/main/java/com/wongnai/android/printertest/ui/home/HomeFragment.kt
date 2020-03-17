package com.wongnai.android.printertest.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.wongnai.android.printertest.R
import com.wongnai.android.printertest.ui.printer.PrintersViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val printersViewModel: PrintersViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printButton.setOnClickListener {
            printersViewModel.print()
        }
    }
}
