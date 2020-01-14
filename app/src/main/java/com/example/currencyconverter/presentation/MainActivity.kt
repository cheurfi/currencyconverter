package com.example.currencyconverter.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.factory.CurrencyConverterViewModelFactory
import com.example.currencyconverter.model.CurrencyBase
import com.example.currencyconverter.network.CurrencyConverterClient
import com.example.currencyconverter.repository.CurrencyConverterRepository
import com.example.currencyconverter.viewmodel.CurrencyConverterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.fixedRateTimer

class MainActivity : BaseActivity() {

    private val recyclerView: RecyclerView? by lazy { recycler_view }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = CurrencyConverterRepository(CurrencyConverterClient())
        val factory = CurrencyConverterViewModelFactory(repository)
        model = ViewModelProviders.of(this, factory).get(CurrencyConverterViewModel::class.java)
        model.currencyItems.observe(this, currencyItemsObserver)

        timer = fixedRateTimer("timer", false, 0L, 1000) {
        launch(Dispatchers.IO) {
                model.getOrganisations()
            }
        }
        recyclerView?.layoutManager = LinearLayoutManager(this)
    }

    private val currencyItemsObserver: Observer<CurrencyBase> = Observer {
        val rates = it.rates
        recyclerView?.adapter = CurrencyConverterAdapter(rates)
    }

    override fun onDestroy() {
        super.onDestroy()
        model.currencyItems.removeObserver(currencyItemsObserver)
    }
}
