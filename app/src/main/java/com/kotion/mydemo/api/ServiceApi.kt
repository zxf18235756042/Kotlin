package com.kotion.mydemo.api

import com.kotion.mydemo.base.BaseApi
import com.kotion.mydemo.base.BaseResult
import com.kotion.mydemo.data.*
import okhttp3.RequestBody
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

    /**
     * 获取品牌数据
     */
    @GET("api/tag/brand")
    suspend fun getBrand(@Query("page") page:Int,@Query("size") size:Int):BaseResult<BrandData>


    /**
     * 获取商品数据
     */
    @GET("api/tag/goods")
    suspend fun getGoods(@Query("page") page:Int,@Query("size") size:Int):BaseResult<GoodData>

    /**
     * 提交动态
     */
    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("api/trends/submitTrends")
    suspend fun submitTrends(@Body body: RequestBody):BaseResult<SubmitData>

    /**
     * 刷新token
     */
    @POST("api/auth/refreshToken")
    suspend fun refreshToken():BaseResult<String>


    /**
     * 动态点赞
     */
    @POST("api/trends/trendsGood")
    @FormUrlEncoded
    suspend fun postTrendsGood(@Field("trendsid") trendsid:Int):BaseResult<TrendsGoodData>

    /**
     * 所有的点赞动态的数据
     */
    @GET("api/trends/getAllTrendsGood")
    suspend fun getAllTrendsGood():BaseResult<List<Int>>
}