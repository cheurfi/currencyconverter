package com.example.currencyconverter.repository

import com.example.currencyconverter.model.CurrencyBase
import com.example.currencyconverter.network.CurrencyConverterClient

class CurrencyConverterRepository(private val client: CurrencyConverterClient) {

    suspend fun getCurrencyItems(): CurrencyBase? {
        val response = client.service.getCurrencyItems().await()
        return if (response.isSuccessful) response.body()
        else null
    }
}