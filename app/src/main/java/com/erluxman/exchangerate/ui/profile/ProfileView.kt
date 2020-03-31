package com.erluxman.exchangerate.ui.profile

import android.view.ViewGroup
import com.erluxman.exchangerate.R
import com.erluxman.exchangerate.model.profile.OpenExchangePageEvent
import com.erluxman.exchangerate.model.profile.ProfileEvent
import com.erluxman.exchangerate.model.profile.ProfileModel
import com.erluxman.exchangerate.ui.common.BaseView
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_profile.view.*
class ProfileView(parent: ViewGroup) : BaseView<ProfileModel, ProfileEvent>(parent) {
    override val layout: Int = R.layout.fragment_profile

    override fun bindView(model: Observable<ProfileModel>): Observable<ProfileEvent> {
        disposable += model.subscribe {

        }
        return Observable.mergeArray(
            containerView.goToExchangeRates.clicks().map { OpenExchangePageEvent }
        )
    }

}