package com.example.currencyconverter.presentation

import android.text.Editable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.converter_item.view.*

class CurrencyConverterViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val currencyImage: ImageView by lazy { view.currency_image }
    private val currencyName: TextView by lazy { view.currency_title }
    private val currencyValue: TextInputEditText by lazy { view.currency_value }

    fun bind(currencyImage: Int?, currencyName: String?, currencyValue: Double?) {
        currencyImage?.let { this.currencyImage.setImageResource(it) }
        this.currencyName.text = currencyName
        this.currencyValue.text = currencyValue.toString() as Editable
    }
}