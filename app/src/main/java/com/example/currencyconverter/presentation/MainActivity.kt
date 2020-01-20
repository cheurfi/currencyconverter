package com.example.currencyconverter.presentation

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.factory.CurrencyConverterViewModelFactory
import com.example.currencyconverter.model.Rate
import com.example.currencyconverter.network.CurrencyConverterClient
import com.example.currencyconverter.repository.CurrencyConverterRepository
import com.example.currencyconverter.viewmodel.CurrencyConverterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.fixedRateTimer

class MainActivity : BaseActivity() {

    private val recyclerView: RecyclerView? by lazy { recycler_view }
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CurrencyConverterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = CurrencyConverterRepository(CurrencyConverterClient())
        val factory = CurrencyConverterViewModelFactory(repository)
        model = ViewModelProviders.of(this, factory).get(CurrencyConverterViewModel::class.java)

            timer = fixedRateTimer("timer", false, 0L, 1000) {
                launch(Dispatchers.IO) {
                    model.getOrganisations()
                }
        }
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = linearLayoutManager
        adapter = CurrencyConverterAdapter()
        recyclerView?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        model.currencyItems.observe(this, currencyItemsObserver)
    }

    private val currencyItemsObserver: Observer<List<Rate>> = Observer {
        adapter.setData(it)
    }

    override fun onDestroy() {
        super.onDestroy()
        model.currencyItems.removeObserver(currencyItemsObserver)
    }
}
