package com.mudassirkhan.currencyexchangerevoult.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mudassirkhan.currencyexchangerevoult.R
import com.mudassirkhan.currencyexchangerevoult.feature.fragment.AlertsFragment
import com.mudassirkhan.currencyexchangerevoult.feature.fragment.RatesFragment
import com.mudassirkhan.currencyexchangerevoult.feature.main.CurrencyExchangeFragment

/**
 * FragmentPagerAdapter implementation to display the main fragments
 */
class TabFragmentAdapter(val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {
        const val TabRates = 0
        const val TabConverter = 1
        const val TabAlerts = 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            TabRates -> RatesFragment()
            TabConverter -> CurrencyExchangeFragment()
            TabAlerts -> AlertsFragment()
            else -> error(
                    "there is no type that matches the position $position + make sure your using adapter correctly")
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            TabRates -> context.getString(R.string.title_rates)
            TabConverter -> context.getString(R.string.title_converter)
            TabAlerts -> context.getString(R.string.title_alerts)
            else -> ""
        }
    }

}