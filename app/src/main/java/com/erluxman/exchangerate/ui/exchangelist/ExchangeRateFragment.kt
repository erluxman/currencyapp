package com.erluxman.exchangerate.ui.exchangelist

import android.view.ViewGroup
import com.erluxman.exchangerate.model.rate.ExchangeRateEffect
import com.erluxman.exchangerate.model.rate.ExchangeRateEvent
import com.erluxman.exchangerate.model.rate.ExchangeRateModel
import com.erluxman.exchangerate.presentation.common.Component
import com.erluxman.exchangerate.ui.common.BaseMobiusFragment

class ExchangeRateFragment :
    BaseMobiusFragment<ExchangeRateModel, ExchangeRateEvent, ExchangeRateEffect>() {
    override fun getComponent(container: ViewGroup?): Component<ExchangeRateModel, ExchangeRateEvent, ExchangeRateEffect> {
        return ExchangeRateComponentBuilder().build(container!!)
    }
}