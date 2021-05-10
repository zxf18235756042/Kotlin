package com.kotion.mydemo.ui.more.adapters

import android.content.Context
import android.util.SparseArray
import android.view.View
import androidx.databinding.ViewDataBinding
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseAdapter
import com.kotion.mydemo.data.BrandData
import com.kotion.mydemo.evts.ClickEvt

class BrandAdapter (
    context: Context,
    list: ArrayList<BrandData.Data>,
    layTypes:SparseArray<Int>,
    var clickEvt: ClickEvt
): BaseAdapter<BrandData.Data>(context,list,layTypes){
    override fun layoutId(position: Int): Int {
        return R.layout.layout_brand_item
    }

    override fun bindData(binding: ViewDataBinding, data: BrandData.Data, layId: Int) {
        binding.root.tag=data
        binding.root.setOnClickListener(View.OnClickListener {
            clickEvt.clickListener(it)
        })
    }
}