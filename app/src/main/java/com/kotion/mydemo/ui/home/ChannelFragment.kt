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
import com.kotion.mydemo.api.Constants
import com.kotion.mydemo.base.BaseFragment
import com.kotion.mydemo.data.TrendsDataItem
import com.kotion.mydemo.databinding.FragmentChannelBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.ui.home.adapters.TrendsAdapter
import com.kotion.mydemo.vm.home.ChannelViewModel
import com.kotion.mydemo.widget.RecyDecoration
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_editimag.*
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

    //是否正在加载数据
    private var isLoadData:Boolean=false

    private var currentGoodTrendsId:Int=0

    override fun initView() {
        layoutTypes.set(R.layout.layout__trends_item, BR.trendsData)
        trendsAdapter= TrendsAdapter(mContext,list,layoutTypes,this)
        //recyclerview.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        manager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerview.layoutManager=manager
        recyclerview.adapter=trendsAdapter
        recyclerview.addItemDecoration(RecyDecoration(16,5))

        layout_refresh.setRefreshHeader(ClassicsHeader(mContext))
        layout_refresh.setRefreshFooter(ClassicsFooter(mContext))
        /**
         * 加载更多列表数据
         */
        layout_refresh.setOnLoadMoreListener {
            if(!isLoadData){
                isLoadData = true
                page ++
                var map = HashMap<String,String>()
                map.put("command",commandId.toString())
                map.put("channelid",channelId.toString())
                map.put("page",page.toString())
                map.put("size",size.toString())
                mViewModel.getTrendsList(map)
            }
        }
    }

    override fun initVM() {
        //加载数据返回的监听
        mViewModel.trendsData.observe(this,{
            Log.i("TAG", "initView: "+it.toString())
            trendsAdapter!!.addData(it)
            isLoadData = false
            if(layout_refresh.isLoading){
                layout_refresh.finishLoadMore(500)
            }
        })

        //刷新列表的最新数据
        mViewModel.trendsData.observe(this,{
            isLoadData = false
            trendsAdapter!!.addData(0,it)
            if(layout_refresh.isLoading){
                layout_refresh.finishRefresh()
            }
        })


        //监听点赞返回
        mViewModel.trendsGoodResult.observe(this,{
            updateListGoodState()
            trendsAdapter!!.notifyDataSetChanged()
        })
    }

    /**
     * 刷新本地list列表中的点赞数据
     */
    private fun updateListGoodState(){
        if(currentGoodTrendsId > 0){
            for(item in list){
                if(item.id == currentGoodTrendsId){
                    item.goods++
                    break
                }
            }
            Constants.allTrendsGoods.add(currentGoodTrendsId)
            currentGoodTrendsId = 0
        }
    }
    override fun initData() {
        var map = HashMap<String,String>()
        map.put("command",commandId.toString())
        map.put("channelid",channelId.toString())
        map.put("page",page.toString())
        map.put("size",size.toString())
        mViewModel.getTrendsList(map)
    }

    override fun initVariable() {

    }

    override fun clickListener(v: View) {
        when(v.id){
            R.id.img_good -> {
                Log.i("TAG","good")
                var data = v.tag as TrendsDataItem
                currentGoodTrendsId = data.id
                mViewModel.postTrendsGood(data.id)
            }
        }
    }


}
