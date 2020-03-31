package com.erluxman.exchangerate.ui.exchangelist

import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erluxman.exchangerate.R
import com.erluxman.exchangerate.model.rate.ExchangeRateEffect
import com.erluxman.exchangerate.model.rate.ExchangeRateEvent
import com.erluxman.exchangerate.model.rate.ExchangeRateModel
import com.erluxman.exchangerate.presentation.common.Component
import com.erluxman.exchangerate.presentation.exchangerate.ExchangeRateFragmentCommands
import com.erluxman.exchangerate.ui.common.BaseMobiusFragment

class ExchangeRateFragment :
    BaseMobiusFragment<ExchangeRateModel, ExchangeRateEvent, ExchangeRateEffect>(),
    ExchangeRateFragmentCommands {
    override fun getComponent(container: ViewGroup?): Component<ExchangeRateModel, ExchangeRateEvent, ExchangeRateEffect> {
        return ExchangeRateComponentBuilder().build(container!!, this)
    }

    override fun openProfile() {
        findNavController().navigate(R.id.action_exchange_to_profile)
    }
}