package com.example.currencyconverter.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyBase(

	@SerializedName("base")
	val base : String,
	@SerializedName("date")
	val date : String,
	@Expose
	@SerializedName("rates")
	val rates : Map<String, Double>
)