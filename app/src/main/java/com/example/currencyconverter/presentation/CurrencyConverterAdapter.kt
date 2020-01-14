package com.example.currencyconverter.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.model.CurrencyItem

class CurrencyConverterAdapter(private val currencyData: List<CurrencyItem>): RecyclerView.Adapter<CurrencyConverterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyConverterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.converter_item, parent, true)
        return CurrencyConverterViewHolder(view)
    }

    override fun getItemCount(): Int = currencyData.size


    override fun onBindViewHolder(holder: CurrencyConverterViewHolder, position: Int) {
        val item = currencyData[position]
        holder.bind(null, item.name, item.value)
    }
}