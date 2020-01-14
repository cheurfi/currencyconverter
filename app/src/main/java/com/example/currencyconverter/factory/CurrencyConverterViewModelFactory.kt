package com.example.currencyconverter.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.repository.CurrencyConverterRepository
import com.example.currencyconverter.viewmodel.CurrencyConverterViewModel

class CurrencyConverterViewModelFactory(private val repository: CurrencyConverterRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyConverterViewModel(repository) as T
    }
}