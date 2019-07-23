package com.mudassirkhan.currencyexchangerevoult.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mudassirkhan.currencyexchangerevoult.feature.main.CurrencyExchangeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyExchangeViewModel::class)
    internal abstract fun provideCurrencyViewModel(viewModel: CurrencyExchangeViewModel): ViewModel
}