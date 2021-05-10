package com.kotion.mydemo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotion.mydemo.net.Repository
import com.shop.utils.MyMmkv

open class BaseViewModel<R>(val repository: R): ViewModel(){
    protected var status:MutableLiveData<Int> = MutableLiveData()

    /**
     * 刷新token的协成调用方法
     */
    suspend fun redreshToken(){
        var result=(repository as Repository).refreshToken()
        MyMmkv.setValue("token",result.data)
    }
}