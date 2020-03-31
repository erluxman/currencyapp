package com.erluxman.exchangerate.presentation.profile

import com.erluxman.exchangerate.model.profile.ProfileEffect
import com.erluxman.exchangerate.model.profile.ProfileEvent
import com.erluxman.exchangerate.model.profile.ProfileModel
import com.erluxman.exchangerate.presentation.common.SubtypeEffectHandlerBuilder
import com.spotify.mobius.First
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer

fun initProfile(model: ProfileModel): First<ProfileModel, ProfileEffect> =
    First.first(model)

fun updateProfile(model: ProfileModel, event: ProfileEvent): Next<ProfileModel, ProfileEffect> {
    return Next.noChange()

}

fun createProfileEffectHandler(
): ObservableTransformer<ProfileEffect, ProfileEvent> {
    return SubtypeEffectHandlerBuilder<ProfileEffect, ProfileEvent>()
        .build()
}
