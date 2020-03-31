package com.erluxman.exchangerate.ui.exchangelist

import android.view.ViewGroup
import com.erluxman.exchangerate.model.rate.ExchangeRateEffect
import com.erluxman.exchangerate.model.rate.ExchangeRateEvent
import com.erluxman.exchangerate.model.rate.ExchangeRateModel
import com.erluxman.exchangerate.presentation.common.Component
import com.erluxman.exchangerate.presentation.exchangerate.*

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
            effectFun = createExchangeRateEffectHandler(commands, ExchangeRateUseCases()),
            defaultScreenModel = ExchangeRateModel()
        )
    }
}