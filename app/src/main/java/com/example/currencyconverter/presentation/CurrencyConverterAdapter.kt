package com.example.currencyconverter.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import java.util.*

class CurrencyConverterAdapter : RecyclerView.Adapter<CurrencyConverterViewHolder>(),
    CurrencyConverterViewHolder.ItemClickListener {

    private var currencyData: Map<String, Double> = emptyMap()
    private lateinit var viewHolder: CurrencyConverterViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyConverterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.converter_item, parent, false)
        viewHolder = CurrencyConverterViewHolder(view, this)
        return viewHolder
    }

    override fun getItemCount(): Int = currencyData.size

    override fun onBindViewHolder(holder: CurrencyConverterViewHolder, position: Int) {
        if (currencyData.isEmpty()) {
            val name = currencyData.keys.elementAt(position)
            val value = currencyData.values.elementAt(position)
            holder.bind(null, name, value)
        } else holder.bind(currencyData.values.elementAt(position))
    }

    override fun onItemClicked() {
        Collections.swap(currencyData.toList(), viewHolder.adapterPosition, 0)
        notifyItemMoved(viewHolder.adapterPosition, 0)
    }

    fun setData(data: Map<String, Double>) {
        currencyData = data
        notifyDataSetChanged()
    }
}