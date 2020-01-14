package com.example.currencyconverter.network

import com.example.currencyconverter.model.CurrencyBase
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyConverterService {

    @GET("/latest?base=EUR")
    fun getCurrencyItems(): Deferred<Response<CurrencyBase>>
}