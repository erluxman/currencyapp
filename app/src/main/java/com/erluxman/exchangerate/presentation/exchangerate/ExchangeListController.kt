package com.erluxman.exchangerate.presentation.exchangerate

import com.erluxman.exchangerate.model.rate.*
import com.erluxman.exchangerate.presentation.common.SubtypeEffectHandlerBuilder
import com.spotify.mobius.First
import com.spotify.mobius.Next
import com.spotify.mobius.Next.dispatch
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers

fun initExchangeRate(model: ExchangeRateModel): First<ExchangeRateModel, ExchangeRateEffect> =
    First.first(model)

fun updateExchangeRate(
    model: ExchangeRateModel,
    event: ExchangeRateEvent
): Next<ExchangeRateModel, ExchangeRateEffect> {
    return when (event) {
        is OpenProfileEvent -> dispatch(setOf(OpenProfileEffect))
        else -> Next.noChange()
    }
}

fun createExchangeRateEffectHandler(
    commands: ExchangeRateFragmentCommands
): ObservableTransformer<ExchangeRateEffect, ExchangeRateEvent> {
    return SubtypeEffectHandlerBuilder<ExchangeRateEffect, ExchangeRateEvent>()
        .addFunction(handleOpenProfile(commands), AndroidSchedulers.mainThread())
        .build()
}

fun handleOpenProfile(commands: ExchangeRateFragmentCommands): (OpenProfileEffect) -> (HandShakeEvent) =
    {
        commands.openProfile()
        HandShakeEvent
    }

interface ExchangeRateFragmentCommands {
    fun openProfile()
}
