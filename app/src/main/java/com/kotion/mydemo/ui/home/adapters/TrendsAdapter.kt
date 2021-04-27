package com.kotion.mydemo.ui.home.adapters

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.kotion.mydemo.BR
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseAdapter
import com.kotion.mydemo.data.TrendsDataItem
import com.kotion.mydemo.evts.ClickEvt

class TrendsAdapter (
    context: Context,
    list:ArrayList<TrendsDataItem>,
    layoutTypes:SparseArray<Int>,
    var clickEvt: ClickEvt
):BaseAdapter<TrendsDataItem>(context,list,layoutTypes) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout__trends_item
    }

    override fun bindData(binding: ViewDataBinding, data: TrendsDataItem, layId: Int) {
        binding.setVariable(BR.clickEvt,clickEvt)
    }
}