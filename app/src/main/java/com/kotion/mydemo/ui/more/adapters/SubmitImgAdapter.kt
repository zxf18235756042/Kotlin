package com.kotion.mydemo.ui.more.adapters

import android.content.Context
import android.util.SparseArray
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseAdapter
import com.kotion.mydemo.data.GridImgData
import com.kotion.mydemo.evts.ClickEvt


class SubmitImgAdapter(
    context: Context,
    list:ArrayList<GridImgData>,
    layoutTypes:SparseArray<Int>,
    var clickEvt: ClickEvt
): BaseAdapter<GridImgData>(context,list,layoutTypes) {
    override fun layoutId(position: Int): Int {
        return R.layout.layout_submit_img
    }

    override fun bindData(binding: ViewDataBinding, data: GridImgData, layId: Int) {
        var img = binding.root.findViewById<ImageView>(R.id.img)
        if(data.path.isNullOrEmpty()){
            img.setImageResource(R.mipmap.ic_addimg)
            img.setOnClickListener({
                clickEvt.clickListener(img)
            })
        }else{
            Glide.with(img).load(data.path).into(img)
            if (img.hasOnClickListeners())
                img.setOnClickListener(null)
        }
    }
}