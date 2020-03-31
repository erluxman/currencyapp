package com.erluxman.exchangerate.data.common

import android.content.Context
import com.erluxman.exchangerate.data.exchangerate.EXCHANGE_RATE_BASE_URL
import com.erluxman.exchangerate.data.exchangerate.ExchangeRateAPI
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient private constructor(applicationContext: Context) {
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private var cache = Cache(applicationContext.cacheDir, 20 * 1024 * 1024)

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(chain.request())
        }
        .cache(cache)
        .build()

    private fun buildRetrofit(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        //.client(httpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val exChangeAPI: ExchangeRateAPI = buildRetrofit(
        EXCHANGE_RATE_BASE_URL
    )
        .create(ExchangeRateAPI::class.java)

    companion object {
        fun instance(applicationContext: Context) =
            RestClient(applicationContext)
    }
}