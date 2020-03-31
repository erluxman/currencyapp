package com.erluxman.exchangerate.data.exchangerate

import com.erluxman.exchangerate.model.rate.ExchangeRateModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val appId = "4c9a4346f1af438694e108e960c8f35e"
const val EXCHANGE_RATE_BASE_URL = "https://openexchangerates.org/api/"

interface ExchangeRateAPI {

    @GET("latest.json")
    fun getUserInfoObservable(@Query("app_id") app_id: String = appId): Observable<ExchangeRateModel>

}