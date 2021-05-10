package com.kotion.mydemo.vm.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.data.TrendsData
import com.kotion.mydemo.data.TrendsGoodData
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository
import kotlinx.coroutines.launch

class ChannelViewModel:BaseViewModel<Repository>(Inject.repository) {
    /**
     * 主页列表数据
     */
    var trendsData:MutableLiveData<TrendsData> = MutableLiveData()

    /**
     * 最新的动态数据
     */
    var newTrendsData:MutableLiveData<TrendsData> = MutableLiveData()

    /**
     * 点赞返回
     */
    var trendsGoodResult:MutableLiveData<TrendsGoodData> = MutableLiveData()

    fun getTrendsList(map:Map<String,String>){
        viewModelScope.launch {
            var result = repository.getTrendsList(map)
            trendsData.postValue(result.data)
        }
    }


    /**
     * 刷新加载最新的动态数据
     */
    fun refreshTrendsList(map:Map<String,String>){
        viewModelScope.launch {
            var result = repository.getTrendsList(map)
            newTrendsData.postValue(result.data)
        }
    }

    /**
     * 点赞
     */
    fun postTrendsGood(trendsid:Int){
        viewModelScope.launch {
            var result = repository.postTrendsGood(trendsid)
            trendsGoodResult.postValue(result.data)
        }
    }
}