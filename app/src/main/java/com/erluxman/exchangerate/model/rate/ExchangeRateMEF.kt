package com.erluxman.exchangerate.model.rate

import com.google.gson.annotations.SerializedName

data class ExchangeRateModel(

    @SerializedName("base")
    val baseCurrency: String = "",
    @SerializedName("rates")
    val exChangeRates: Map<String, String> = mapOf()
)

sealed class ExchangeRateEvent
sealed class ExchangeRateEffect

object OpenProfileEvent:ExchangeRateEvent()
object OpenProfileEffect:ExchangeRateEffect()