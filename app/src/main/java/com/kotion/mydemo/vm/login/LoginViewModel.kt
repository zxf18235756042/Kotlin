package com.kotion.mydemo.vm.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotion.mydemo.api.MyApp
import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.data.LoginData
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository
import com.kotion.mydemo.utils.SystemUtils
import com.shop.utils.MyMmkv
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel:BaseViewModel<Repository>(Inject.repository) {
    var username:String? = null
    var pw:String? = null

    var loginData:MutableLiveData<LoginData> = MutableLiveData()

    fun login(){


        if(username.isNullOrEmpty() || pw.isNullOrEmpty()){
            return
        }
        var map = HashMap<String,String>()
        map.put("username",username.toString())
        map.put("password",pw.toString())
        var imei = SystemUtils.getIMEI(MyApp.app!!)
        map.put("imei",imei!!)
        var lat = MyMmkv.getString("lat")
        var lng = MyMmkv.getString("lng")
        map.put("lat",lat!!)
        map.put("lng",lng!!)

        viewModelScope.launch {
            var result = repository.login(map)
            if(result.errno == 0){
                try {
                    MyMmkv.setValue("token",result.data.token)
                    MyMmkv.setValue("uid",result.data.userInfo.uid)
                   /* MyMmkv.setValue("avatar",result.data.userInfo.avater)
                    MyMmkv.setValue("nickname",result.data.userInfo.nickname)*/
                }catch (e:IOException){
                    Log.i("TAG",e.message.toString())
                }finally {
                    //数据更新
                    loginData.postValue(result.data)
                }

            }
        }
    }
}