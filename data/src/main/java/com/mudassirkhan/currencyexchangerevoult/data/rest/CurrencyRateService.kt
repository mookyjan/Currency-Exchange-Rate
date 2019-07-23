package com.mudassirkhan.currencyexchangerevoult.data.rest

import com.mudassirkhan.currencyexchangerevoult.data.model.RateList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * Retrofit2 service to access Revolut Rates API
 */
interface CurrencyRateService {

    companion object {
        const val BASE_URL = "https://revolut.duckdns.org"
    }

    @GET("/latest")
    fun getTopTracks(@Query("base") base: String): Single<RateList>
}