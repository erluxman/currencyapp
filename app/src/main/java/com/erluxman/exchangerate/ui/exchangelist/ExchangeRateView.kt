package com.erluxman.exchangerate.ui.exchangelist

import android.util.Log
import android.view.ViewGroup
import com.erluxman.exchangerate.R
import com.erluxman.exchangerate.model.rate.ExchangeRateEvent
import com.erluxman.exchangerate.model.rate.ExchangeRateModel
import com.erluxman.exchangerate.model.rate.OpenProfileEvent
import com.erluxman.exchangerate.ui.common.BaseView
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_exchange_rate.view.*


class ExchangeRateView(parent: ViewGroup) : BaseView<ExchangeRateModel, ExchangeRateEvent>(parent) {
    override val layout: Int = R.layout.fragment_exchange_rate

    override fun bindView(model: Observable<ExchangeRateModel>): Observable<ExchangeRateEvent> {
        disposable += model.subscribe {model->
            Log.d("datatest","baseCurrency ${model.baseCurrency}")
            Log.d("datatest","exchange Rates ${model.exChangeRates}")
        }
        return Observable.mergeArray(
            containerView.goToProfile.clicks().map { OpenProfileEvent }
        )
    }

}