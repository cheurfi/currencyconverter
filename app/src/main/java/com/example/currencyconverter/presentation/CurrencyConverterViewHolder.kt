package com.example.currencyconverter.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.model.Rate
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.converter_item.view.*

class CurrencyConverterViewHolder(
    private val view: View,
    private val listener: ItemClickListener
) : RecyclerView.ViewHolder(view) {

    private val currencyImage: ImageView? by lazy { view.currency_image }
    private val currencyName: TextView? by lazy { view.currency_title }
    private val currencyValue: TextInputEditText? by lazy { view.currency_value }

    fun bind(rate: Rate?) {
        this.currencyImage?.setImageResource(rate?.image ?: R.drawable.na)
        this.currencyName?.text = rate?.name
        this.currencyValue?.setText(rate?.value.toString())
        view.setOnClickListener {
            listener.onItemClicked(rate, adapterPosition)
        }
    }

    interface ItemClickListener {
        fun onItemClicked(rate: Rate?, adapterPosition: Int)
    }
}