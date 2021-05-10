package com.kotion.mydemo.ui.more.adapters

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseAdapter
import com.kotion.mydemo.data.BrandData
import com.kotion.mydemo.data.GoodData
import com.kotion.mydemo.data.HomeData
import com.kotion.mydemo.evts.ClickEvt

class GoodsAdapter (
    context: Context,
    list: ArrayList<GoodData.Data>,
    layoutTypes:SparseArray<Int>,
    var clickEvt: ClickEvt
):BaseAdapter<GoodData.Data>(context,list,layoutTypes){
    override fun layoutId(position: Int): Int {
        return R.layout.layout_good_item
    }

    override fun bindData(binding: ViewDataBinding, data: GoodData.Data, layId: Int) {

    }
}