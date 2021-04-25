package com.kotion.mydemo.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.data.DetailData
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository
import kotlinx.coroutines.launch

class HomeViewModel :BaseViewModel<Repository>(Inject.repository){
    var username:String=""
    var pw:String=""

    var resultType: MutableLiveData<Int> = MutableLiveData()   //当这个值为0 成功 1失败
    var detailData: MutableLiveData<DetailData> = MutableLiveData()  //初始化数据

    var id:Int=6434

    fun getDetail(){
        viewModelScope.launch {
            var result=repository.getDetail(id)
            if (result.ret==0){
                detailData.postValue(result)
            }
        }
    }
    fun login(){
        if (username.isNotEmpty() and pw.isNotEmpty()){
            Log.i("TAG", "login:$username,-----------$pw ")
            resultType.postValue(0)
        }
    }
}