package com.kotion.mydemo.ui.more

import android.content.Intent
import android.util.SparseArray
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotion.mydemo.BR
import com.kotion.mydemo.R
import com.kotion.mydemo.api.Constants
import com.kotion.mydemo.base.BaseActivity
import com.kotion.mydemo.data.ChannelDataItem
import com.kotion.mydemo.databinding.ActivityChannelBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.ui.more.adapters.ChannelSubmitAdapter
import com.kotion.mydemo.vm.more.ChannelSubmitViewModel
import kotlinx.android.synthetic.main.activity_channel.*

class ChannelActivity :BaseActivity<ChannelSubmitViewModel, ActivityChannelBinding>(
    R.layout.activity_channel,ChannelSubmitViewModel::class.java), ClickEvt {

    var list:ArrayList<ChannelDataItem> = arrayListOf()
    lateinit var channelAdapter: ChannelSubmitAdapter

    override fun initView() {
        var layoutTypes: SparseArray<Int> = SparseArray()
        layoutTypes.append(R.layout.layout_submit_channel_item,BR.channelSubmitData)
        channelAdapter = ChannelSubmitAdapter(this,list,layoutTypes,this)
        recy_channel.layoutManager=LinearLayoutManager(this)
        recy_channel.adapter=channelAdapter

    }

    override fun initVM() {
        mViewModel.channel.observe(this,{
            list.addAll(it)
            channelAdapter.notifyDataSetChanged()
        })
    }

    override fun initData() {
        mViewModel.getChannel()
    }

    override fun initVariable() {

    }

    override fun clickListener(v: View) {
        when(v.id){
            R.id. channel_fan ->{
                var tag:ChannelDataItem = v.tag as ChannelDataItem
                var intent = Intent()
                intent.putExtra("channelid",tag.id)
                setResult(Constants.SUBMIT_CHANNEL,intent)
                finish()
            }
        }

    }
}