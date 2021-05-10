package com.kotion.mydemo.vm.more

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.data.BrandData
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository
import kotlinx.coroutines.launch

class BrandViewModel :BaseViewModel<Repository>(Inject.repository){
    //品牌
    var brand:MutableLiveData<BrandData> = MutableLiveData()   //品牌

    var page:Int=1
    var size:Int=10

    fun getBrands(){
        viewModelScope.launch {
            var result = repository.getBrand(page,size)
            if(result.errno == 0){
                brand.postValue(result.data)
                Log.i("TAG","result:"+result.data)
            }
        }
        }

}