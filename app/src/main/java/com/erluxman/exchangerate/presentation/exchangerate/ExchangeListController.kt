package com.erluxman.exchangerate.presentation.exchangerate

import com.erluxman.exchangerate.model.rate.ExchangeRateEffect
import com.erluxman.exchangerate.model.rate.ExchangeRateEvent
import com.erluxman.exchangerate.model.rate.ExchangeRateModel
import com.erluxman.exchangerate.presentation.common.SubtypeEffectHandlerBuilder
import com.spotify.mobius.First
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer


fun initExchangeRate(model: ExchangeRateModel): First<ExchangeRateModel, ExchangeRateEffect> =
    First.first(model)

fun updateExchangeRate(
    model: ExchangeRateModel,
    event: ExchangeRateEvent
): Next<ExchangeRateModel, ExchangeRateEffect> {
    return Next.noChange()

}

fun createExchangeRateEffectHandler(
): ObservableTransformer<ExchangeRateEffect, ExchangeRateEvent> {
    return SubtypeEffectHandlerBuilder<ExchangeRateEffect, ExchangeRateEvent>()
        .build()
}