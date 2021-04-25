package com.kotion.mydemo.api

import com.kotion.mydemo.base.BaseApi
import com.kotion.mydemo.data.DetailData
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceApi :BaseApi{
    @POST("api/cms/detail")
    suspend fun getDetail(@Query("id") id:Int):DetailData
}