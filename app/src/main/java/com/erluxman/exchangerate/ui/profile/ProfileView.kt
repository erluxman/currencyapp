package com.erluxman.exchangerate.ui.profile

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.erluxman.exchangerate.R
import com.erluxman.exchangerate.model.profile.OpenExchangePageEvent
import com.erluxman.exchangerate.model.profile.ProfileEvent
import com.erluxman.exchangerate.model.profile.ProfileModel
import com.erluxman.exchangerate.ui.common.BaseView
import com.erluxman.exchangerate.ui.common.bind
import com.erluxman.exchangerate.ui.utils.loadImage
import com.google.android.material.chip.Chip
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.exchange_rates.view.*
import kotlinx.android.synthetic.main.item_currency_row.view.*
import kotlinx.android.synthetic.main.profile_card.view.*

class ProfileView(parent: ViewGroup) : BaseView<ProfileModel, ProfileEvent>(parent) {
    override val layout: Int = R.layout.fragment_profile
    var chipsAdded = false;
    override fun initLayout(model: ProfileModel?) {
        super.initLayout(model)
        chipsAdded = false;
    }

    override fun bindView(model: Observable<ProfileModel>): Observable<ProfileEvent> {
        disposable += model.subscribe { profile ->
            containerView.name.text =
                profile.userProfile.name + ", " + profile.userProfile.age.toString()
            containerView.profileImage.loadImage(profile.userProfile.imageUrl, circular = true)
            val currencies = getCurrencyListFromMap(profile.exchangeRates.exChangeRates)

            containerView.currencyRates.bind(
                currencies,
                R.layout.item_currency_row,
                LinearLayoutManager(getContext())
            ) { entry ->
                currencySymbol.text = entry.symbol
                currencyRate.text = entry.value.toString()
            }
            if (!chipsAdded) profile.userProfile.tags.forEach { tag ->
                val chip = Chip(parent?.context)
                chip.text = tag
                containerView.tag_group.addView(chip)
                chipsAdded = true
            }
        }
        return Observable.mergeArray(
            containerView.goToExchangeRates.clicks().map { OpenExchangePageEvent }
        )
    }


}

data class Currency(val symbol: String, val value: Float)

fun getCurrencyListFromMap(values: Map<String, Float>): List<Currency> {
    val keys = values.keys.toList()
    val currencies = mutableListOf<Currency>()
    keys.forEach { key ->
        currencies.add(Currency(key, values[key]!!))
    }
    return currencies
}