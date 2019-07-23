package com.mudassirkhan.currencyexchangerevoult.domain.usecase

import com.mudassirkhan.currencyexchangerevoult.data.model.RateList
import com.mudassirkhan.currencyexchangerevoult.data.repository.CurrencyRateRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 * Usecase to retrieve currency rates
 */
class CurrencyRateUsecase @Inject constructor(private val currencyRateRepository: CurrencyRateRepository) {

    /**
     * Returns the currency rates using a base symbol
     */
    fun getRates(base: String): Single<RateList> {
        return currencyRateRepository.getRates(base)
    }
}