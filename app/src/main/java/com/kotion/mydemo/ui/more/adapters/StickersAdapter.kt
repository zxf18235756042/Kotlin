package com.kotion.mydemo.ui.more.adapters

import android.content.Context
import android.util.SparseArray
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseAdapter
import com.kotion.mydemo.data.StickersData
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.utils.AssetsUtils

class StickersAdapter(
    context: Context,
    list:ArrayList<StickersData.Img>,
    layouTypes:SparseArray<Int>,
    var clickEvt: ClickEvt
):BaseAdapter<StickersData.Img>(context,list,layouTypes){
    override fun layoutId(position: Int): Int {
        return R.layout.layout_stickers_item
    }

    override fun bindData(binding: ViewDataBinding, data: StickersData.Img, layId: Int) {
        var imgview=binding.root.findViewById<ImageView>(R.id.img_sticker)
        var rid =AssetsUtils.getStickersIconByName(data.url)
        imgview.setImageResource(rid)
        imgview.tag=data
        imgview.setOnClickListener({
            if (clickEvt!=null){
                clickEvt.clickListener(it)
            }
        })
    }

}