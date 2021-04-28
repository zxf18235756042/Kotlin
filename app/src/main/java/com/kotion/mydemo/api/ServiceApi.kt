package com.kotion.mydemo.api

import com.kotion.mydemo.base.BaseApi
import com.kotion.mydemo.base.BaseResult
import com.kotion.mydemo.data.*
import retrofit2.http.*

interface ServiceApi :BaseApi{

    @Headers("newurl:http://api.qizhongy.com/")
    @POST("api/cms/detail")
    suspend fun getDetail(@Query("id") id:Int):DetailData


    @Headers("newurl:https://cdwan.cn/")
    @GET("api/index")
    suspend fun getIndex(): HomeData
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

    /**
     * 登录
     */
    @POST("api/auth/login")
    @FormUrlEncoded
    suspend fun login(@FieldMap map: Map<String, String>):BaseResult<LoginData>


    /**
     * 注册
     */
    @POST("api/auth/register")
    @FormUrlEncoded
    suspend fun register(@FieldMap map: Map<String, String>):BaseResult<RegisterData>

}