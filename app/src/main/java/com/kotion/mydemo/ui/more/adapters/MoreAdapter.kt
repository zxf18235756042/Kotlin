package com.kotion.mydemo.ui.more.adapters

import android.content.Context
import android.util.SparseArray
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.kotion.mydemo.BR
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseAdapter

import com.kotion.mydemo.evts.ClickEvt

/**
 *   author ：H C
 *   time ：2021/4/28
 */
class MoreAdapter(
    context:Context,
    list:ArrayList<String>,
    layoutTypes:SparseArray<Int>,
    var clickEvt: ClickEvt
):BaseAdapter<String>(context,list,layoutTypes){
    override fun layoutId(position: Int): Int {
        return R.layout.layout_more_item
    }

    override fun bindData(binding: ViewDataBinding, data: String, layId: Int) {
        var img = binding.root.findViewById<ImageView>(R.id.img_item)
        Glide.with(img).load(data!!).into(img)
        binding.root.tag=data
        binding.setVariable(BR.clickEvt,clickEvt)
    }

}