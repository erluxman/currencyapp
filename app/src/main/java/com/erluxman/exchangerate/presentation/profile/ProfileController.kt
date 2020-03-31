package com.erluxman.exchangerate.presentation.profile

import com.erluxman.exchangerate.model.profile.*
import com.erluxman.exchangerate.presentation.common.SubtypeEffectHandlerBuilder
import com.spotify.mobius.First
import com.spotify.mobius.Next
import com.spotify.mobius.Next.dispatch
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread

fun initProfile(model: ProfileModel): First<ProfileModel, ProfileEffect> =
    First.first(model)

fun updateProfile(model: ProfileModel, event: ProfileEvent): Next<ProfileModel, ProfileEffect> {
    return when (event) {
        OpenExchangePageEvent -> dispatch(setOf(OpenExchangePageEffect))
        else -> Next.noChange()
    }


}

fun createProfileEffectHandler(
    commands: ProfileFragmentCommands
): ObservableTransformer<ProfileEffect, ProfileEvent> {
    return SubtypeEffectHandlerBuilder<ProfileEffect, ProfileEvent>()
        .addFunction(handleOpenExchangeRate(commands), mainThread())
        .build()
}

fun handleOpenExchangeRate(commands: ProfileFragmentCommands): (OpenExchangePageEffect) -> (HandShakeEvent) =
    {
        commands.openExchangeRates()
        HandShakeEvent
    }

interface ProfileFragmentCommands {
    fun openExchangeRates()
}