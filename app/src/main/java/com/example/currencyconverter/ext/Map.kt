package com.example.currencyconverter.ext

import com.example.currencyconverter.model.Rate

fun Map<String, Double>.toListOfRates(): List<Rate> {
    val listOfRates = mutableListOf<Rate>()
    this.forEach {
        listOfRates.add(Rate(it.key, it.value))
    }
    return listOfRates
}