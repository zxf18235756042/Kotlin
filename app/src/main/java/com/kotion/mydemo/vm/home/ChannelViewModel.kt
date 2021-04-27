package com.kotion.mydemo.vm.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.data.TrendsData
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository
import kotlinx.coroutines.launch

class ChannelViewModel:BaseViewModel<Repository>(Inject.repository) {
    /**
     * 主页列表数据
     */
    var trendsData:MutableLiveData<TrendsData> = MutableLiveData()

    fun getTrendsList(map:Map<String,String>){
        viewModelScope.launch {
            var result = repository.getTrendsList(map)
            trendsData.postValue(result.data)
        }
    }
}