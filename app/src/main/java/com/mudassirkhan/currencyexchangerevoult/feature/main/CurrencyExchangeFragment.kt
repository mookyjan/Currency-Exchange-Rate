package com.mudassirkhan.currencyexchangerevoult.feature.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter

import android.content.Context
import android.os.Bundle


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.mudassirkhan.currencyexchangerevoult.adapter.CurrencyRateAdapter
import com.mudassirkhan.currencyexchangerevoult.databinding.FragmentCurrencyExchangeBinding
import com.mudassirkhan.currencyexchangerevoult.di.ViewModelFactory
import com.mudassirkhan.currencyexchangerevoult.listener.OnAmountChangedListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_currency_exchange.*
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * Fragment displaying to currency exchange rate
 */
class CurrencyExchangeFragment : Fragment() {


    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    private lateinit var adapter: CurrencyRateAdapter
    lateinit var currencyExchangeViewModel: CurrencyExchangeViewModel

    private lateinit var mBinding: FragmentCurrencyExchangeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = FragmentCurrencyExchangeBinding.inflate(inflater, container, false)

        adapter = CurrencyRateAdapter(object : OnAmountChangedListener {

            override fun onAmountChanged(symbol: String, amount: Float) {
                Timber.d("CurrencyValue $amount $symbol")
                recyclerCurrencies.smoothScrollToPosition(0)
                currencyExchangeViewModel.checkCurrencyRates(symbol, amount)
            }

        })
        initView()
        setupObservers()
        setupSwipeToRefresh()
        return mBinding.root
    }

    /**
     * here the refresh listener only for concept no need because data already refresh after 1 sec
     */
    private fun setupSwipeToRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener(refreshListener)

    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        currencyExchangeViewModel.callAPI()
        adapter.notifyDataSetChanged()
        mBinding.swipeRefreshLayout.isRefreshing = false
    }


    /**
     * Setup view
     */
    private fun initView() {
        mBinding.recyclerCurrencies.setHasFixedSize(true)
        mBinding.recyclerCurrencies.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerCurrencies.adapter = adapter
    }

    private fun setupObservers() {
        currencyExchangeViewModel.amountValue.observe(this, Observer {

            adapter.updateAmount(it)
        })

        currencyExchangeViewModel.rateList.observe(this, Observer {

            adapter.updateRates(it.toList())
        })

        currencyExchangeViewModel.loading.observe(this, Observer {

            showLoading(it)
        })

        currencyExchangeViewModel.isError.observe(this, Observer {

            if (it != null) {
                Snackbar.make(mBinding.root, "Error ${it.localizedMessage}", Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        currencyExchangeViewModel = ViewModelProviders.of(this, this.viewModeFactory).get(CurrencyExchangeViewModel::class.java)

    }


    fun showLoading(isLoading: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

        recyclerCurrencies.visibility = if (isLoading) View.GONE else View.VISIBLE
        recyclerCurrencies.animate().setDuration(shortAnimTime.toLong()).alpha(
                (if (isLoading) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                recyclerCurrencies.visibility = if (isLoading) View.GONE else View.VISIBLE
            }
        })

        progressView.visibility = if (isLoading) View.VISIBLE else View.GONE
        progressView.animate().setDuration(shortAnimTime.toLong()).alpha(
                (if (isLoading) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                progressView.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        })
    }


}