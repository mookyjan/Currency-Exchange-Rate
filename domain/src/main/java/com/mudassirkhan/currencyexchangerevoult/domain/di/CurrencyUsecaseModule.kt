package com.mudassirkhan.currencyexchangerevoult.domain.di

import com.mudassirkhan.currencyexchangerevoult.data.repository.CurrencyRateRepository
import com.mudassirkhan.currencyexchangerevoult.domain.usecase.CurrencyRateUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger2 Module to provides Usecases dependencies
 */
@Module
class CurrencyUsecaseModule {

    @Singleton
    @Provides
    internal fun providesRateUsecase(currencyRateRepository: CurrencyRateRepository): CurrencyRateUsecase {
        return CurrencyRateUsecase(currencyRateRepository)
    }
}