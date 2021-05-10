package com.kotion.mydemo.vm.more

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.data.BrandData
import com.kotion.mydemo.data.GoodData
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository
import kotlinx.coroutines.launch

class GoodsViewModel : BaseViewModel<Repository>(Inject.repository) {
    var brand: MutableLiveData<MutableList<BrandData>> = MutableLiveData()  //品牌
    var good: MutableLiveData<MutableList<GoodData>> = MutableLiveData()  //商品数据

    var page: Int = 0
    var size: Int = 0

    /**
     * 获取品牌数据
     */
    fun getBrands(){
        viewModelScope.launch {
            var result=repository.getBrand(page,size)
        }
    }
    /**
     * 获取商品数据
     */
    fun getGood(){

    }
}