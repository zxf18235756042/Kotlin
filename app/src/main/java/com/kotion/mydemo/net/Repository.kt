package com.kotion.mydemo.net

import com.kotion.mydemo.api.ServiceApi
import com.kotion.mydemo.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository:BaseRepository<ServiceApi>(ServiceApi::class.java) {
    /**
     * 获取详情数据的的接口
     */
    suspend fun getDetail(id:Int)= withContext(Dispatchers.IO){
        api!!.getDetail(id)
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
}