package com.kotion.mydemo.net

import com.kotion.mydemo.api.ServiceApi
import com.kotion.mydemo.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

class Repository:BaseRepository<ServiceApi>(ServiceApi::class.java) {
    /**
     * 获取详情数据的的接口
     */
    suspend fun getDetail(id:Int)= withContext(Dispatchers.IO){
        api!!.getDetail(id)
    }

    suspend fun getIndex() = withContext(Dispatchers.IO){
        api!!.getIndex()
    }

    /**
     * 获取频道分类数据
     */
    suspend fun getChannel()= withContext(Dispatchers.IO){
        api!!.getChannel()
    }

    /**
     * 获取主页的列表数据
     */
    suspend fun getTrendsList(map:Map<String,String>) = withContext(Dispatchers.IO){
        api!!.getTrendsList(map)
    }

    /**
     * 登录
     */
    suspend fun login(map:Map<String,String>) = withContext(Dispatchers.IO){
        api!!.login(map)
    }

    /**
     * 注册
     */
    suspend fun register(map:Map<String,String>) = withContext(Dispatchers.IO){
        api!!.register(map)
    }

    /**
     * 获取品牌数据
     */
    suspend fun getBrand(page:Int,size:Int)= withContext(Dispatchers.IO){
        api!!.getBrand(page,size)
    }
    /**
     * 获取商品数据
     */
    suspend fun getGoods(page:Int,size:Int)= withContext(Dispatchers.IO){
        api!!.getGoods(page,size)
    }
    suspend fun submitData(body: RequestBody) = withContext(Dispatchers.IO){
        api!!.submitTrends(body)
    }

    suspend fun refreshToken() = withContext(Dispatchers.IO){
        api!!.refreshToken()
    }

    suspend fun postTrendsGood(trendsid:Int) = withContext(Dispatchers.IO){
        api!!.postTrendsGood(trendsid)
    }

    suspend fun getAllTrendsGood() = withContext(Dispatchers.IO){
        api!!.getAllTrendsGood()
    }

}