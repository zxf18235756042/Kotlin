package com.kotion.mydemo.ui.home

import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.core.util.set
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kotion.mydemo.BR
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseFragment
import com.kotion.mydemo.data.TrendsDataItem
import com.kotion.mydemo.databinding.FragmentChannelBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.ui.home.adapters.TrendsAdapter
import com.kotion.mydemo.vm.home.ChannelViewModel
import com.kotion.mydemo.widget.RecyDecoration
import kotlinx.android.synthetic.main.fragment_channel.*

class ChannelFragment(
    var commandId: Int,
    var channelId: Int
) : BaseFragment<ChannelViewModel, FragmentChannelBinding>(
    R.layout.fragment_channel,
    ChannelViewModel::class.java
),ClickEvt {
    lateinit var manager:StaggeredGridLayoutManager
    private var page:Int=1
    private var size:Int=10

    private var layoutTypes:SparseArray<Int> = SparseArray()
    private var trendsAdapter: TrendsAdapter?=null
    private var list:ArrayList<TrendsDataItem> = arrayListOf()
    override fun initView() {
        layoutTypes.set(R.layout.layout__trends_item, BR.trendsData)
        trendsAdapter= TrendsAdapter(mContext,list,layoutTypes,this)
        //recyclerview.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        manager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerview.layoutManager=manager
        recyclerview.adapter=trendsAdapter
        recyclerview.addItemDecoration(RecyDecoration(16,5))
    }

    override fun initVM() {
        mViewModel.trendsData.observe(this,{
            trendsAdapter!!.refreshData(it)
        })
    }

    override fun initData() {
        var map = HashMap<String, String>()
        map.put("command", commandId.toString())
        map.put("channelid", channelId.toString())
        map.put("page", page.toString())
        map.put("size", size.toString())
        mViewModel.getTrendsList(map)
    }

    override fun initVariable() {
        var map = HashMap<String,String>()
        map.put("command",commandId.toString())
        map.put("channelid",channelId.toString())
        map.put("page",page.toString())
        map.put("size",size.toString())
        mViewModel.getTrendsList(map)
    }

    override fun clickListener(v: View) {
        when(v.id){
            R.id.img_good -> {
                Log.i("TAG","good")
            }
        }
    }
}