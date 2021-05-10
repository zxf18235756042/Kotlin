package com.kotion.mydemo.ui.more.adapters

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.kotion.mydemo.BR
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseAdapter
import com.kotion.mydemo.data.ChannelDataItem
import com.kotion.mydemo.evts.ClickEvt


class ChannelSubmitAdapter(
    context: Context,
    list: ArrayList<ChannelDataItem>,
    layoutTypes:SparseArray<Int>,
    var clickEvt: ClickEvt
): BaseAdapter<ChannelDataItem>(context,list,layoutTypes) {
    override fun layoutId(position: Int): Int {
        return R.layout.layout_submit_channel_item
    }

    override fun bindData(binding: ViewDataBinding, data: ChannelDataItem, layId: Int) {
        binding.root.tag = data
        binding.setVariable(BR.channelSubmitEvt, clickEvt)
    }
}