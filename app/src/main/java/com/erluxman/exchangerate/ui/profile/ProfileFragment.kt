package com.erluxman.exchangerate.ui.profile

import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erluxman.exchangerate.R
import com.erluxman.exchangerate.model.profile.ProfileEffect
import com.erluxman.exchangerate.model.profile.ProfileEvent
import com.erluxman.exchangerate.model.profile.ProfileModel
import com.erluxman.exchangerate.presentation.common.Component
import com.erluxman.exchangerate.presentation.profile.ProfileFragmentCommands
import com.erluxman.exchangerate.ui.common.BaseMobiusFragment

class ProfileFragment : BaseMobiusFragment<ProfileModel, ProfileEvent, ProfileEffect>(),
    ProfileFragmentCommands {
    override fun getComponent(container: ViewGroup?): Component<ProfileModel, ProfileEvent, ProfileEffect> {
        return ProfileComponentBuilder().build(container!!, this)
    }

    override fun openExchangeRates() {
        findNavController().navigate(R.id.action_profile_to_exchangeRate)
    }
}