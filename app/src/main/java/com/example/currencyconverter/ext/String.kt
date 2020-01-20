package com.example.currencyconverter.ext

import com.example.currencyconverter.R

fun String.mapImage(): Int? {
    return when(this) {
        "CAD" -> R.drawable.ic_canada
        "EUR" -> R.drawable.ic_eu
        "SEK" -> R.drawable.ic_sweden
        "USD" -> R.drawable.ic_us
        else -> null
    }
}