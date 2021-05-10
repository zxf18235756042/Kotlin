package com.kotion.mydemo.vm.more

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.data.SubmitData
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody


class SubmitViewModel: BaseViewModel<Repository>(Inject.repository) {

    var submitResult:MutableLiveData<SubmitData> = MutableLiveData()

    /**
     * 提交动态的网络接口
     */
    fun submit(content:String){
        var body=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),content)
        viewModelScope.launch {
            var result = repository.submitData(body)
            if (result.errno==0){
                submitResult.postValue(result.data)
            }else if (result.errno==604){
                redreshToken()//刷新
                submit(content)
            }
        }
    }
}