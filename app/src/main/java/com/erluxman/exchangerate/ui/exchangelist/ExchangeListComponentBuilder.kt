package com.erluxman.exchangerate.ui.exchangelist

import android.view.ViewGroup
import com.erluxman.exchangerate.model.rate.ExchangeRateEffect
import com.erluxman.exchangerate.model.rate.ExchangeRateEvent
import com.erluxman.exchangerate.model.rate.ExchangeRateModel
import com.erluxman.exchangerate.presentation.common.Component
import com.erluxman.exchangerate.presentation.exchangerate.ExchangeRateFragmentCommands
import com.erluxman.exchangerate.presentation.exchangerate.createExchangeRateEffectHandler
import com.erluxman.exchangerate.presentation.exchangerate.initExchangeRate
import com.erluxman.exchangerate.presentation.exchangerate.updateExchangeRate

class ExchangeRateComponentBuilder {

    fun build(
        parent: ViewGroup,
        commands: ExchangeRateFragmentCommands
    )
            : Component<ExchangeRateModel, ExchangeRateEvent, ExchangeRateEffect> {
        val view = ExchangeRateView(parent)
        return Component(
            initFun = ::initExchangeRate,
            updateFun = ::updateExchangeRate,
            connectToViewFun = view::bindView,
            view = view,
            effectFun = createExchangeRateEffectHandler(commands),
            defaultScreenModel = ExchangeRateModel()
        )
    }
}