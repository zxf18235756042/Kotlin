package com.kotion.mydemo.net

import com.kotion.mydemo.api.ServiceApi
import com.kotion.mydemo.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository:BaseRepository<ServiceApi>(ServiceApi::class.java) {
    suspend fun getDetail(id:Int)= withContext(Dispatchers.IO){
        api!!.getDetail(id)
    }
}