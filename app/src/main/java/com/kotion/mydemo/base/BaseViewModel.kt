package com.kotion.mydemo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<R>(val repository: R): ViewModel(){
    protected var status:MutableLiveData<Int> = MutableLiveData()
}