package com.wongnai.android.printertest.di

import com.wongnai.android.printertest.ui.printer.PrintersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PrintersViewModel(get()) }
}
