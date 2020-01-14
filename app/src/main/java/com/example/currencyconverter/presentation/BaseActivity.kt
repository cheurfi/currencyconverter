package com.example.currencyconverter.presentation

import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.viewmodel.CurrencyConverterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.*
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity: CoroutineScope, AppCompatActivity() {
    lateinit var timer: Timer
    lateinit var model: CurrencyConverterViewModel
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        job.cancel()
    }
}