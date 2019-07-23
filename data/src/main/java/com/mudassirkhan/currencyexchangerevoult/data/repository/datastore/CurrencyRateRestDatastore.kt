package com.mudassirkhan.currencyexchangerevoult.data.repository.datastore

import com.mudassirkhan.currencyexchangerevoult.data.model.RateList
import com.mudassirkhan.currencyexchangerevoult.data.rest.CurrencyRateService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Datastore implementation to access Rates using Rest API
 */
class CurrencyRateRestDatastore @Inject constructor(private val currencyRateService: CurrencyRateService) : CurrencyRateDatastore {

    override fun getRates(base: String): Single<RateList> {
        return currencyRateService.getTopTracks(base)
    }
}