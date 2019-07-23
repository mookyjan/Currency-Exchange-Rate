package com.mudassirkhan.currencyexchangerevoult

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.mudassirkhan.currencyexchangerevoult.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * CurrencyExchangeApp application
 */
class CurrencyExchangeApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatcher: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatcher: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatcher

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatcher

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        DaggerAppComponent
                .builder()
                .application(this)
                .context(this)
                .build()
                .inject(this)
    }
}