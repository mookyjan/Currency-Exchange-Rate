package com.mudassirkhan.currencyexchangerevoult.di

import android.app.Application
import android.content.Context
import com.mudassirkhan.currencyexchangerevoult.CurrencyExchangeApp
import com.mudassirkhan.currencyexchangerevoult.data.di.RepositoryModule
import com.mudassirkhan.currencyexchangerevoult.data.di.RestModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger2 Component to inject modules
 */
@Singleton
@Component(modules = [
    ActivityModule::class,
    FragmentModule::class,
    ViewModelFactoryModule::class,
    RestModule::class,
    RepositoryModule::class])
interface AppComponent {

    fun inject(application: CurrencyExchangeApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}