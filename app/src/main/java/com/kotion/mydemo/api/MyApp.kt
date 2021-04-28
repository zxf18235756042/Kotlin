package com.kotion.mydemo.api

import android.app.Application
import com.kotion.mydemo.base.BaseApi
import com.shop.utils.MyMmkv

class MyApp :Application(){
    companion object{
        var app: MyApp?=null
    }
    override fun onCreate() {
        super.onCreate()
        app =this
        MyMmkv.initMMKV()
        BaseApi.baseUrl ="http://sprout.cdwan.cn/"
    }
}