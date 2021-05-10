package com.kotion.mydemo.ui.home.adapters

import android.content.Context
import android.provider.SyncStateContract
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.kotion.mydemo.BR
import com.kotion.mydemo.R
import com.kotion.mydemo.api.Constants
import com.kotion.mydemo.base.BaseAdapter
import com.kotion.mydemo.data.TrendsDataItem
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.utils.UserUtils

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
        val imgGood=binding.root.findViewById<ImageView>(R.id.img_good)
        imgGood.tag = data
        /**
         * 点赞数据的判断
         */
        if(UserUtils.isGoods(data.id)){
            imgGood.setImageResource(R.mipmap.ic_like)
        }else{
            imgGood.setImageResource(R.mipmap.tab_like_normal)
        }
        val imgType = binding.root.findViewById<ImageView>(R.id.img_type)
        if (data.type==Constants.TYPE_IMAGE){
            if (data.res.size>1){
                imgType.visibility= View.VISIBLE
                imgType.setImageResource(R.mipmap.ic_type_imgs)
            }else{
                imgType.visibility= View.GONE
            }
        }else if (data.type==Constants.TYPE_VIDEO){
            imgType.setImageResource(R.mipmap.ic_type_play)
        }
    }
}