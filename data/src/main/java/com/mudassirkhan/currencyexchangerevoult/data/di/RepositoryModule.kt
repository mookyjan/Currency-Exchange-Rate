package com.mudassirkhan.currencyexchangerevoult.data.di

import com.mudassirkhan.currencyexchangerevoult.data.repository.CurrencyRateRepository
import com.mudassirkhan.currencyexchangerevoult.data.repository.datastore.CurrencyRateRestDatastore
import com.mudassirkhan.currencyexchangerevoult.data.rest.CurrencyRateService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesCurrencyRateRestDatastore(currencyRateService: CurrencyRateService): CurrencyRateRestDatastore = CurrencyRateRestDatastore(currencyRateService)


    @Singleton
    @Provides
    fun providesRateRepository(rateRestDatastore: CurrencyRateRestDatastore): CurrencyRateRepository = CurrencyRateRepository(rateRestDatastore)

}
