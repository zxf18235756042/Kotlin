package com.kotion.mydemo.vm.more

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.data.ChannelData
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository
import kotlinx.coroutines.launch

class ChannelSubmitViewModel :BaseViewModel<Repository>(Inject.repository){

    var channel: MutableLiveData<ChannelData> = MutableLiveData()


    fun getChannel(){
        viewModelScope.launch {
            var result = repository.getChannel()
            channel.postValue(result.data)
        }
    }

}