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
    var resultType:MutableLiveData<Int> = MutableLiveData(0)

    var detailData:MutableLiveData<DetailData> = MutableLiveData()  //初始化数据

    var id:Int = 6434
    fun login(){
        if(username.isNotEmpty()&&pw.isNotEmpty()){
            //网络请求
            Log.i("TAG", "login: username $username,pw $pw")
            resultType.postValue(0)
        }
    }
    fun getDetail(){
        viewModelScope.launch {
            var result = repository.getDetail(id)
            if(result.ret == 0){
                detailData.postValue(result)
            }
        }
    }

    fun getIndex(){
        viewModelScope.launch {
            var result = repository.getIndex()
            if(result.errno == 200){
                Log.i("TAG",result.toString())
            }
        }
    }
}