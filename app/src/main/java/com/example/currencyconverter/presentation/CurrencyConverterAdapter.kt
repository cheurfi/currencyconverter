package com.example.currencyconverter.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.model.CurrencyItem

class CurrencyConverterAdapter(private val currencyData: Map<String, Double>): RecyclerView.Adapter<CurrencyConverterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyConverterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.converter_item, parent, false)
        return CurrencyConverterViewHolder(view)
    }

    override fun getItemCount(): Int = currencyData.size


    override fun onBindViewHolder(holder: CurrencyConverterViewHolder, position: Int) {
        val name = currencyData.keys.elementAt(position)
        val value = currencyData.values.elementAt(position)
        holder.bind(null, name, value)
    }
}