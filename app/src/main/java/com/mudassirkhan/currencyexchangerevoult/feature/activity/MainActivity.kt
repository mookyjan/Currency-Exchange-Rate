package com.mudassirkhan.currencyexchangerevoult.feature.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mudassirkhan.currencyexchangerevoult.R
import com.mudassirkhan.currencyexchangerevoult.adapter.TabFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var tabFragmentAdapter: TabFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabFragmentAdapter = TabFragmentAdapter(this, supportFragmentManager!!)
        initView()
    }

    /**
     * Setup the view
     */
    private fun initView() {
        mainViewPager.adapter = tabFragmentAdapter
        tabLayout.setupWithViewPager(mainViewPager)
        mainViewPager.currentItem = 1
    }
}
