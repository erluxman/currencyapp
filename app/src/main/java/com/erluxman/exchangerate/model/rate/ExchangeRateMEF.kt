package com.erluxman.exchangerate.model.rate

import com.erluxman.exchangerate.model.profile.ProfileEffect
import com.erluxman.exchangerate.model.profile.ProfileEvent
import com.google.gson.annotations.SerializedName

data class ExchangeRateModel(

    @SerializedName("base")
    val baseCurrency: String = "",
    @SerializedName("rates")
    val exChangeRates: Map<String, Float> = mapOf()
)

sealed class ExchangeRateEvent
sealed class ExchangeRateEffect
data class ExchangeRateFetchSuccessEvent(val exchangeRates: ExchangeRateModel) : ExchangeRateEvent()

object OpenProfileEvent:ExchangeRateEvent()
object HandShakeEvent:ExchangeRateEvent()
object FetchExchangeRateEffect : ExchangeRateEffect()
object OpenProfileEffect:ExchangeRateEffect()