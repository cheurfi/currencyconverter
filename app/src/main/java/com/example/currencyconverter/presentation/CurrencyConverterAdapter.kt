package com.example.currencyconverter.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.model.Rate

class CurrencyConverterAdapter :
    RecyclerView.Adapter<CurrencyConverterViewHolder>(),
    CurrencyConverterViewHolder.ItemClickListener {

    private var currencyData: MutableList<Rate> = mutableListOf()
    private lateinit var viewHolder: CurrencyConverterViewHolder
    private var itemClicked = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyConverterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.converter_item, parent, false)
        viewHolder = CurrencyConverterViewHolder(view, this)
        return viewHolder
    }

    override fun getItemCount(): Int = currencyData.size

    override fun onBindViewHolder(holder: CurrencyConverterViewHolder, position: Int) {
        val item = currencyData[position]
        holder.bind(item)
    }

    override fun onItemClicked(rate: Rate?, adapterPosition: Int) {
        currencyData.removeAt(adapterPosition)
        rate?.let { currencyData.add(0, it) }
        itemClicked = true
        notifyDataSetChanged()
    }

    fun setData(data: List<Rate>) {
        currencyData = if (!itemClicked) {
            data.toMutableList()
        } else {
            val list = mutableListOf<Rate>()
            currencyData.forEachIndexed { index, localItem ->
                data.forEach { remoteItem ->
                    if (remoteItem.name == localItem.name) {
                        list.add(index, remoteItem)
                    }
                }
            }
            list
        }
        notifyDataSetChanged()
    }
}