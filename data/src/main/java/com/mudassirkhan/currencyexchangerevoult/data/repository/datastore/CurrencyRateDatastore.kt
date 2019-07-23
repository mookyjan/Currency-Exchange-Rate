package com.mudassirkhan.currencyexchangerevoult.data.repository.datastore

import com.mudassirkhan.currencyexchangerevoult.data.model.RateList
import io.reactivex.Single

/**
 * Datastore interface to access rates
 */
interface CurrencyRateDatastore {

    /**
     * Returns the rates using a base symbol
     */
    fun getRates(base: String): Single<RateList>

}