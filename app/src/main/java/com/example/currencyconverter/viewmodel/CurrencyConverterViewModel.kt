package com.example.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.ext.mapImage
import com.example.currencyconverter.ext.toListOfRates
import com.example.currencyconverter.model.Rate
import com.example.currencyconverter.repository.CurrencyConverterRepository

class CurrencyConverterViewModel(private val repository: CurrencyConverterRepository): ViewModel() {

    private val _currencyItems = MutableLiveData<List<Rate>>()
    val currencyItems: LiveData<List<Rate>>
        get() = _currencyItems

    suspend fun getOrganisations() {
        val currencyItems = repository.getCurrencyItems()
        val remoteRates = currencyItems?.rates?.toListOfRates()
        val rates = mutableListOf<Rate>()
        val baseItem = currencyItems?.base?.let { Rate(it, 0.0, R.drawable.ic_eu) }
        baseItem?.let { rates.add(it) }
        remoteRates?.forEach {  rate ->
            rates.add(Rate(rate.name, rate.value, rate.name.mapImage()))
        }
        _currencyItems.postValue(rates)
    }
}