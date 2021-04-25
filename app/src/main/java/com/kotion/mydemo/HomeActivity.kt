package com.kotion.mydemo

import android.util.Log
import android.view.View
import androidx.lifecycle.observe
import com.kotion.mydemo.base.BaseActivity
import com.kotion.mydemo.databinding.ActivityHomeBinding

import com.kotion.mydemo.vm.HomeViewModel

class HomeActivity : BaseActivity<HomeViewModel,ActivityHomeBinding>(R.layout.activity_home,HomeViewModel::class.java),ClickEvt {
    override fun initView(){
        mViewModel.resultType.observe(this,{
            when(it){
                0 -> {
                    Log.i("TAG","it $it")
                    finish()
                }
                1 -> {
                    Log.i("TAG","it $it")
                }
            }
        })


    }


    override fun clickListener(v:View){
        when(v.id){
            R.id.btn_login -> {
                mViewModel.login()
            }
        }
    }

    override fun initVM() {

    }

    override fun initData() {
        mViewModel.getDetail()
    }

    override fun initVariable() {
        mDataBinding!!.clickEvt = this
        mDataBinding!!.homeVM = mViewModel
    }
}