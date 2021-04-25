package com.kotion.mydemo.base

import com.kotion.mydemo.net.RetrofitFactory

open class BaseRepository<S>(rCla:Class<S>) {
    protected var api:S? =null
    init{
        api= RetrofitFactory.instance.create(rCla)
    }
}