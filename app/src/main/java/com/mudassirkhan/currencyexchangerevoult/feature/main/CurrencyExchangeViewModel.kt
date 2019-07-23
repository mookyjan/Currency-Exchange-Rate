package com.mudassirkhan.currencyexchangerevoult.feature.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mudassirkhan.currencyexchangerevoult.data.model.Rate
import com.mudassirkhan.currencyexchangerevoult.domain.usecase.CurrencyRateUsecase
//import com.mudassirkhan.currencyexchangerevoult.view.ConverterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrencyExchangeViewModel @Inject constructor(private val currencyRatesUsecase: CurrencyRateUsecase) : ViewModel() {

    companion object {
        const val DEFAULT_SYMBOL = "EUR"
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var amountValue = MutableLiveData<Float>()
    val loading = MutableLiveData<Boolean>()
    var rateList: MutableLiveData<List<Rate>> = MutableLiveData()
    private var currentBase: String = ""
    private var isLoaded = false
    var isError = MutableLiveData<Throwable>()

    init {
        callAPI()
    }

    /**
     * Update the rates using the given symbol; or simply update the amount
     */
    fun checkCurrencyRates(base: String, amount: Float) {
        //If the base didn't changed, we simply update the amount
        if (base.equals(currentBase, ignoreCase = true)) {
            amountValue.postValue(amount)
        } else {
            currentBase = base.toUpperCase()
            val disposable: Disposable = currencyRatesUsecase.getRates(base)
                    .doOnSubscribe {
                        if (!isLoaded) {
                            loading.postValue(true)
                        }
                    }
                    .delay(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .repeatUntil({ !base.equals(currentBase, ignoreCase = true) })
                    .subscribe({
                        val rates = arrayListOf<Rate>()
                        rates.add(Rate(it.base, 1.0F))
                        rates.addAll(it.rates.map { Rate(it.key, it.value) })
                        val currencies = arrayListOf<Rate>().apply {
                            add(Rate(it.base, 1.0F))
                        }
                        it.rates.forEach { (k, v) ->
                            currencies.add(Rate(k, v))
                        }
                        rateList.postValue(currencies)
                        if (!isLoaded) {
                            loading.postValue(false)
                        }
                        isLoaded = true
                    }, {
                        //TODO this can be improved more only for simplicity
                        loading.postValue(false)
                        parseError(it)
                        it.printStackTrace()
                    })

            compositeDisposable.add(disposable)
        }
    }


    private fun parseError(it: Throwable) {
        isError.postValue(it)
    }

    /**
     * call the api
     */
    fun callAPI() {
        checkCurrencyRates(DEFAULT_SYMBOL, 1F)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        Timber.d("onCleared of viewModel ")
    }
}