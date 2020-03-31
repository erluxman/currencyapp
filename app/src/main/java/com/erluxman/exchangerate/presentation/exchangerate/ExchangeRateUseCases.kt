package com.erluxman.exchangerate.presentation.exchangerate

import com.erluxman.exchangerate.data.common.RestClient
import com.erluxman.exchangerate.model.rate.ExchangeRateModel
import io.reactivex.Observable

data class ExchangeRateUseCases(
    val fetchExchangeRateUseCase: FetchExchangeRateUseCase = FetchExchangeRateUseCase(),
    val updateFavouriteExchangeRateUseCase: UpdateFavouriteExchangeRateUseCase = UpdateFavouriteExchangeRateUseCase()
)

class FetchExchangeRateUseCase {
    operator fun invoke() : Observable<ExchangeRateModel> {
        return RestClient.instance().exChangeAPI.getUserInfoObservable()
    }
}

class UpdateFavouriteExchangeRateUseCase {

    fun saveFavExchangeRate(symbol: String) {

    }

    fun removeFavExchangeRate(symbol: String) {

    }
}