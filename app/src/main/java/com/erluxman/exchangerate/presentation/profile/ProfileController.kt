package com.erluxman.exchangerate.presentation.profile

import com.erluxman.exchangerate.model.profile.*
import com.erluxman.exchangerate.presentation.common.SubtypeEffectHandlerBuilder
import com.erluxman.exchangerate.presentation.exchangerate.ExchangeRateUseCases
import com.erluxman.exchangerate.presentation.exchangerate.FetchExchangeRateUseCase
import com.spotify.mobius.First
import com.spotify.mobius.Next
import com.spotify.mobius.Next.dispatch
import com.spotify.mobius.Next.next
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxkotlin.mergeAll

fun initProfile(model: ProfileModel): First<ProfileModel, ProfileEffect> =
    First.first(model, setOf(FetchProfileEffect))

fun updateProfile(model: ProfileModel, event: ProfileEvent): Next<ProfileModel, ProfileEffect> {
    return when (event) {
        OpenExchangePageEvent -> dispatch(setOf(OpenExchangePageEffect))
        is ProfileFetchSuccessEvent -> next(model.copy(exchangeRates = event.exchangeRates))
        is HandShakeEvent -> Next.noChange()
    }
}

fun createProfileEffectHandler(
    commands: ProfileFragmentCommands,
    useCases: ExchangeRateUseCases
): ObservableTransformer<ProfileEffect, ProfileEvent> {
    return SubtypeEffectHandlerBuilder<ProfileEffect, ProfileEvent>()
        .addFunction(handleOpenExchangeRate(commands), mainThread())
        .addTransformer(fetchProfile(useCases.fetchExchangeRateUseCase))
        .build()
}

fun handleOpenExchangeRate(commands: ProfileFragmentCommands): (OpenExchangePageEffect) -> (HandShakeEvent) =
    {
        commands.openExchangeRates()
        HandShakeEvent
    }

fun fetchProfile(useCase: FetchExchangeRateUseCase):
        ObservableTransformer<FetchProfileEffect, ProfileEvent> {
    return ObservableTransformer { effect ->
        effect.map {
            useCase()
        }.mergeAll().map {
            ProfileFetchSuccessEvent(it)
        }
    }
}

interface ProfileFragmentCommands {
    fun openExchangeRates()
}