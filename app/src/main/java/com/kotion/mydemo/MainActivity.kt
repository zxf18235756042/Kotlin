package com.kotion.mydemo

import android.content.Intent
import android.view.View
import com.kotion.mydemo.base.BaseActivity
import com.kotion.mydemo.databinding.ActivityMainBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.ui.home.HomeFragment
import com.kotion.mydemo.ui.login.LoginActivity
import com.kotion.mydemo.ui.more.MoreActivity
import com.kotion.mydemo.vm.MainViewModel
import com.shop.utils.MyMmkv

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(
    R.layout.activity_main
    , MainViewModel::class.java
) , ClickEvt {
    override fun initView() {
        //LocationJavaUtils.getInstance(this)
        initMainfragment()
    }

    private fun initMainfragment() {
        var trans=supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragments,HomeFragment.homeFragment).commit()
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {
        mDataBinding.mainEvt = this
    }

    override fun clickListener(v: View) {
        when (v.id) {
            R.id.layout_more -> {
                val token = MyMmkv.getString("token")
                if (token.isNullOrEmpty()) {
                    var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    var intent = Intent(this, MoreActivity::class.java)
                     startActivity(intent)
                }
            }
        }
    }

}