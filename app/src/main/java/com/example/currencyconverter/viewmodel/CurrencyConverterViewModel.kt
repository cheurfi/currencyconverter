package com.example.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.model.CurrencyBase
import com.example.currencyconverter.repository.CurrencyConverterRepository

class CurrencyConverterViewModel(private val repository: CurrencyConverterRepository): ViewModel() {

    private val _currencyItems = MutableLiveData<CurrencyBase>()
    val currencyItems: LiveData<CurrencyBase>
        get() = _currencyItems

    suspend fun getOrganisations() = _currencyItems.postValue(repository.getCurrencyItems())
}