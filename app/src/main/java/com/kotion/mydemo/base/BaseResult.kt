package com.kotion.mydemo.base

data class BaseResult<T> (
    val errno:Int,
    val errmsg:String,
    var data:T
)