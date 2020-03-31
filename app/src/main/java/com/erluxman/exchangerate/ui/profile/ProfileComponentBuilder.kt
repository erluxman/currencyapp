package com.erluxman.exchangerate.ui.profile

import android.view.ViewGroup
import com.erluxman.exchangerate.model.profile.ProfileEffect
import com.erluxman.exchangerate.model.profile.ProfileEvent
import com.erluxman.exchangerate.model.profile.ProfileModel
import com.erluxman.exchangerate.presentation.common.Component
import com.erluxman.exchangerate.presentation.exchangerate.ExchangeRateUseCases
import com.erluxman.exchangerate.presentation.profile.ProfileFragmentCommands
import com.erluxman.exchangerate.presentation.profile.createProfileEffectHandler
import com.erluxman.exchangerate.presentation.profile.initProfile
import com.erluxman.exchangerate.presentation.profile.updateProfile

class ProfileComponentBuilder {

    fun build(parent: ViewGroup,
              commands: ProfileFragmentCommands
    )
            : Component<ProfileModel, ProfileEvent, ProfileEffect> {
        val view = ProfileView(parent)
        return Component(
            initFun = ::initProfile,
            updateFun = ::updateProfile,
            connectToViewFun = view::bindView,
            view = view,
            effectFun = createProfileEffectHandler(commands, ExchangeRateUseCases()),
            defaultScreenModel = ProfileModel()
        )
    }
}
