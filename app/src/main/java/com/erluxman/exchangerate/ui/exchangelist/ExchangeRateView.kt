package com.erluxman.exchangerate.ui.exchangelist

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.erluxman.exchangerate.R
import com.erluxman.exchangerate.model.rate.ExchangeRateEvent
import com.erluxman.exchangerate.model.rate.ExchangeRateModel
import com.erluxman.exchangerate.model.rate.OpenProfileEvent
import com.erluxman.exchangerate.ui.common.BaseView
import com.erluxman.exchangerate.ui.common.bind
import com.erluxman.exchangerate.ui.profile.getCurrencyListFromMap
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.exchange_rates.view.*
import kotlinx.android.synthetic.main.fragment_exchange_rate.view.*
import kotlinx.android.synthetic.main.item_currency_row.view.*


class ExchangeRateView(parent: ViewGroup) : BaseView<ExchangeRateModel, ExchangeRateEvent>(parent) {
    override val layout: Int = R.layout.fragment_exchange_rate

    override fun bindView(model: Observable<ExchangeRateModel>): Observable<ExchangeRateEvent> {
        disposable += model.subscribe ({rates->
            Log.d("datatest","baseCurrency ${rates.baseCurrency}")
            Log.d("datatest","exchange Rates ${rates.exChangeRates}")
            val currencies = getCurrencyListFromMap(rates.exChangeRates)

            containerView.currencyRates.bind(
                currencies,
                R.layout.item_currency_row,
                LinearLayoutManager(getContext())
            ) { entry ->
                currencySymbol.text = entry.symbol
                currencyRate.text = entry.value.toString()
            }
        },{
            Log.e("errorlog",it.message)})
        return Observable.mergeArray(
            containerView.goToProfile.clicks().map { OpenProfileEvent }
        )
    }

}