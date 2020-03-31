package com.erluxman.exchangerate.model.profile

import com.erluxman.exchangerate.model.rate.ExchangeRateEvent
import com.erluxman.exchangerate.model.rate.ExchangeRateModel

data class ProfileModel(
    val exchangeRates: ExchangeRateModel = ExchangeRateModel(),
    val userProfile: UserProfile = UserProfile.newInstance()
)

data class UserProfile(
    val name: String,
    val age: Int,
    val imageUrl: String,
    val tags: List<String>
) {
    companion object {
        fun newInstance() = UserProfile(
            "John Doe",
            29,
            "https://pbs.twimg.com/profile_images/1057989852942270464/bt45DHmR.jpg",
            listOf("🏔 Hiking", "🐶 Dog owner", "🏔 Hiking", "🐶 Dog owner", "🕯 Candle light dinner")
        )
    }
}


sealed class ProfileEvent
object OpenExchangePageEvent : ProfileEvent()
object HandShakeEvent : ProfileEvent()
data class ProfileFetchSuccessEvent(val exchangeRates: ExchangeRateModel) : ProfileEvent()
sealed class ProfileEffect
object OpenExchangePageEffect : ProfileEffect()
object FetchProfileEffect : ProfileEffect()



