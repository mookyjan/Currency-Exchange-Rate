package com.mudassirkhan.currencyexchangerevoult.data.repository

import com.mudassirkhan.currencyexchangerevoult.data.model.RateList
import com.mudassirkhan.currencyexchangerevoult.data.repository.datastore.CurrencyRateRestDatastore
import io.reactivex.Single
import javax.inject.Inject

/**
 * Repository to access rates
 */
class CurrencyRateRepository @Inject constructor(private val rateRestDatastore: CurrencyRateRestDatastore) {

    /**
     * Returns the rates using a base symbol
     */
    fun getRates(base: String): Single<RateList> {
        return rateRestDatastore.getRates(base)
    }
}