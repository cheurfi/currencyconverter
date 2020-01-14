package com.example.currencyconverter.network

import com.example.currencyconverter.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CurrencyConverterClient {

    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()

    companion object {
        const val CONNECT_TIMEOUT = 60000L
        const val WRITE_TIMEOUT = 60000L
        const val READ_TIMEOUT = 60000L
    }


    private val gson: Gson
        get() = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

    internal val service: CurrencyConverterService
        get() = retrofit.create(CurrencyConverterService::class.java)
}