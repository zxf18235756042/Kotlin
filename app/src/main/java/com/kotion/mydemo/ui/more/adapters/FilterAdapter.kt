package com.kotion.mydemo.ui.more.adapters

import android.content.Context
import android.util.SparseArray
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.kotion.mydemo.BR
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseAdapter
import com.kotion.mydemo.data.FilterData
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.utils.AssetsUtils

class FilterAdapter(
    context: Context,
    list: ArrayList<FilterData>,
    layoutTypes:SparseArray<Int>,
    var clickEvt: ClickEvt
    ) :BaseAdapter<FilterData>(context,list,layoutTypes){
    override fun layoutId(position: Int): Int {
        return R.layout.layout_filter_item
    }

    override fun bindData(binding: ViewDataBinding, data: FilterData, layId: Int) {
        binding.setVariable(BR.filterData,data)
        var img =binding.root.findViewById<ImageView>(R.id.img_filter)
        var rid =AssetsUtils.getResIdByName(data.icon)
        img.setImageResource(rid)
        binding.root.tag=data
        binding.root.setOnClickListener({v->
            if (clickEvt!=null){
                clickEvt.clickListener(v)
            }
        })
    }
}