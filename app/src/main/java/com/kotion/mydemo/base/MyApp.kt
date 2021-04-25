package com.kotion.mydemo.base

import android.app.Application

class MyApp :Application(){
    override fun onCreate() {
        super.onCreate()
        BaseApi.baseUrl="http://api.qizhongy.com/"
    }
}