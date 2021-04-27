package com.kotion.mydemo.api

import com.kotion.mydemo.base.BaseApi
import com.kotion.mydemo.base.BaseResult
import com.kotion.mydemo.data.ChannelData
import com.kotion.mydemo.data.DetailData
import com.kotion.mydemo.data.TrendsData
import retrofit2.http.*

interface ServiceApi :BaseApi{

    @Headers("newurl:http://api.qizhongy.com/")
    @POST("api/cms/detail")
    suspend fun getDetail(@Query("id") id:Int):DetailData

    /**
     * 频道分类数据
     */
    @GET("api/channel/channel")
    suspend fun getChannel():BaseResult<ChannelData>

    /**
     * 主頁列表数据
     */
    @GET("api/trends/trendsList")
    suspend fun getTrendsList(@QueryMap map:Map<String,String>):BaseResult<TrendsData>
}