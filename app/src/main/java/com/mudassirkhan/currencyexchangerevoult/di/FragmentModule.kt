package com.mudassirkhan.currencyexchangerevoult.di

import com.mudassirkhan.currencyexchangerevoult.feature.main.CurrencyExchangeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Dagger2 Module to provide Fragments dependencies
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun converterFragment(): CurrencyExchangeFragment

}
