package com.mudassirkhan.currencyexchangerevoult.feature.main

import com.mudassirkhan.currencyexchangerevoult.domain.usecase.CurrencyRateUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CurrencyExchangeModule {
    @Singleton
    @Provides
    fun provideExchangeViewModel(currencyRateRepository: CurrencyRateUsecase) =
            CurrencyExchangeViewModel(currencyRateRepository)
}