package com.mudassirkhan.currencyexchangerevoult.adapter


import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mudassirkhan.currencyexchangerevoult.data.model.Rate
import com.mudassirkhan.currencyexchangerevoult.databinding.ItemCurrencyConvertBinding
import com.mudassirkhan.currencyexchangerevoult.listener.OnAmountChangedListener
import com.mudassirkhan.currencyexchangerevoult.util.format
import com.mudassirkhan.currencyexchangerevoult.util.getCurrencyFlagResId
import com.mudassirkhan.currencyexchangerevoult.util.getCurrencyNameResId
import com.mudassirkhan.currencyexchangerevoult.util.toFloat
import java.util.*
import kotlin.collections.ArrayList


class CurrencyRateAdapter(private val onAmountChangedListener: OnAmountChangedListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currencyPosition = ArrayList<String>()
    private val currencyRate = HashMap<String, Rate>()

    private var amount = 1.0F


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyConvertBinding.inflate(inflater, parent, false)
        return CurrencyRateViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currencyPosition.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (!payloads.isEmpty()) {
            (holder as CurrencyRateViewHolder).bind(rateAtPosition(position))
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CurrencyRateViewHolder).bind(rateAtPosition(position))


    }

    /**
     * Update the rate of each currency
     */
    fun updateRates(rates: List<Rate>) {
        if (currencyPosition.isEmpty()) {
            currencyPosition.addAll(rates.map { it.symbol })
        }

        for (rate in rates) {
            currencyRate[rate.symbol] = rate
        }

        notifyItemRangeChanged(0, currencyPosition.size - 1, amount)
    }

    /**
     * Update the amount
     */
    fun updateAmount(amount: Float) {
        this.amount = amount

        notifyItemRangeChanged(0, currencyPosition.size - 1, amount)
    }

    /**
     * Returns the rate at the given position
     */
    private fun rateAtPosition(pos: Int): Rate {
        return currencyRate[currencyPosition[pos]]!!
    }


    /**
     * ViewHolder for the currency Rate
     */
    inner class CurrencyRateViewHolder(itemView: ItemCurrencyConvertBinding)
        : RecyclerView.ViewHolder(itemView.root) {


        var icCurrencyFlag: ImageView = itemView.icCurrencyFlag
        var lblCurrencySymbol: TextView = itemView.lblCurrencySymbol
        var lblCurrencyName: TextView = itemView.lblCurrencyName
        var txtCurrencyAmount: EditText = itemView.txtCurrencyAmount
        var symbol: String = ""


        /**
         * Change the view's values
         */
        fun bind(rate: Rate) {

            if (symbol != rate.symbol) {
                initView(rate)
                this.symbol = rate.symbol
            }

            // If the EditText holds the focus, we don't change the value
            if (!txtCurrencyAmount.isFocused) {
                txtCurrencyAmount.setText((rate.rate * amount).format())
            }
        }

        /**
         * Setup the view
         */
        private fun initView(rate: Rate) {
            val symbol = rate.symbol.toLowerCase()
            val nameId = getCurrencyNameResId(itemView.context, symbol)
            val flagId = getCurrencyFlagResId(itemView.context, symbol)

            lblCurrencySymbol.text = rate.symbol
            lblCurrencyName.text = itemView.context.getString(nameId)
            icCurrencyFlag.setImageResource(flagId)


            txtCurrencyAmount.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                //If view lost focus, we do nothing
                if (!hasFocus) {
                    return@OnFocusChangeListener
                }

                //If view is already on top, we do nothing, otherwise...
                layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
                    //swap the current item to top
                    swapItem(currentPosition, 0)
                    //TODO if don't want to swap and only change the selected item to top
                    //and want to keep the order same is previously then use this
                    //We move it from its current position
//                    currencyPosition.removeAt(currentPosition).also {
//                        //And we add it to the top
//                        currencyPosition.add(0, it)
//                    }
                    //We notify the recyclerview the view moved to position 0
//                    notifyItemMoved(currentPosition, 0)


                }

                txtCurrencyAmount.addTextChangedListener(object : TextWatcher {

                    override fun afterTextChanged(s: Editable?) {

                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        if (txtCurrencyAmount.isFocused) {
                            amount = s.toString().toFloat()
                            onAmountChangedListener.onAmountChanged(symbol, s.toString().toFloat())
                        }
                    }

                })
            }


        }
    }

    /**
     * swap the current item to the top and vice versa
     */
    fun swapItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(currencyPosition, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
}