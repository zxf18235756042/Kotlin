package com.kotion.mydemo

import com.kotion.mydemo.base.BaseActivity
import com.kotion.mydemo.databinding.ActivityMainBinding
import com.kotion.mydemo.ui.home.HomeFragment
import com.kotion.mydemo.vm.MainViewModel

class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>(R.layout.activity_main
,MainViewModel::class.java){
    override fun initView() {
        initMainFragment()
    }

    private fun initMainFragment() {
        var trans =supportFragmentManager.beginTransaction()
        trans.replace(R.id.layout_main,HomeFragment.homeFragment).commit()
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }


}