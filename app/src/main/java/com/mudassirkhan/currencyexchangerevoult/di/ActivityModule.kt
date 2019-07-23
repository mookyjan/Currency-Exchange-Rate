package com.mudassirkhan.currencyexchangerevoult.di

import com.mudassirkhan.currencyexchangerevoult.feature.activity.MainActivity
import com.mudassirkhan.currencyexchangerevoult.feature.main.CurrencyExchangeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [(CurrencyExchangeModule::class)])
    internal abstract fun mainActivity(): MainActivity
}
