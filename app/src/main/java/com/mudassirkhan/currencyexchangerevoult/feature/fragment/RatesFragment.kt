package com.mudassirkhan.currencyexchangerevoult.feature.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mudassirkhan.currencyexchangerevoult.R

/**
 *
 * Fragment displaying the rates
 */
class RatesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rates, container, false)
    }
}