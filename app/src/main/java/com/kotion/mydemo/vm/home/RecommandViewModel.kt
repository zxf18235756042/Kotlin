package com.kotion.mydemo.vm.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.data.ChannelData
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository
import kotlinx.coroutines.launch

class RecommandViewModel : BaseViewModel<Repository>(Inject.repository) {
    var channelData:MutableLiveData<ChannelData> = MutableLiveData()

    fun getChannel(){
        viewModelScope.launch {
            var result = repository.getChannel()
            channelData.postValue(result.data)
        }
    }
}