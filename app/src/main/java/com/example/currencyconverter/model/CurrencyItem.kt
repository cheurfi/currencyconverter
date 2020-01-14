package com.example.currencyconverter.model

import com.google.gson.annotations.Expose

data class CurrencyItem(
    @Expose
    val name: String,
    @Expose
    val value: Double
)