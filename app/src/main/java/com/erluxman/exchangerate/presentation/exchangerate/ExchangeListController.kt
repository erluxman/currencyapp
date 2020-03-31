package com.erluxman.exchangerate.presentation.exchangerate

import com.erluxman.exchangerate.model.rate.*
import com.erluxman.exchangerate.presentation.common.SubtypeEffectHandlerBuilder
import com.spotify.mobius.First
import com.spotify.mobius.Next
import com.spotify.mobius.Next.dispatch
import com.spotify.mobius.Next.next
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.mergeAll

fun initExchangeRate(model: ExchangeRateModel): First<ExchangeRateModel, ExchangeRateEffect> =
    First.first(model, setOf(FetchExchangeRateEffect))

fun updateExchangeRate(
    model: ExchangeRateModel,
    event: ExchangeRateEvent
): Next<ExchangeRateModel, ExchangeRateEffect> {
    return when (event) {
        is OpenProfileEvent -> dispatch(setOf(OpenProfileEffect))
        is ExchangeRateFetchSuccessEvent -> next(event.exchangeRates)
        is HandShakeEvent -> Next.noChange()
    }
}

fun createExchangeRateEffectHandler(
    commands: ExchangeRateFragmentCommands,
    useCases: ExchangeRateUseCases
): ObservableTransformer<ExchangeRateEffect, ExchangeRateEvent> {
    return SubtypeEffectHandlerBuilder<ExchangeRateEffect, ExchangeRateEvent>()
        .addFunction(handleOpenProfile(commands), AndroidSchedulers.mainThread())
        .addTransformer(getAllExchangeRates(useCases.fetchExchangeRateUseCase))
        .build()
}

fun handleOpenProfile(commands: ExchangeRateFragmentCommands): (OpenProfileEffect) -> (HandShakeEvent) =
    {
        commands.openProfile()
        HandShakeEvent
    }

fun getAllExchangeRates(useCase: FetchExchangeRateUseCase):
        ObservableTransformer<FetchExchangeRateEffect, ExchangeRateEvent> {
    return ObservableTransformer { effect ->
        effect.map {
            useCase()
        }.mergeAll().map {
            ExchangeRateFetchSuccessEvent(it)
        }
    }
}

interface ExchangeRateFragmentCommands {
    fun openProfile()
}
