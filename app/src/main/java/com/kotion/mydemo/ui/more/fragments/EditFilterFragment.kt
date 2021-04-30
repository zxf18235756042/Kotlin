package com.kotion.mydemo.ui.more.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kotion.mydemo.R
import com.kotion.mydemo.utils.AssetsUtils
import com.kotion.mydemo.utils.FilterUtils
import kotlinx.android.synthetic.main.fragment_editfilter.*

class EditFilterFragment: Fragment() {
    companion object{
        val instance:EditFilterFragment by lazy {
            EditFilterFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var  view =inflater.inflate(R.layout.fragment_editfilter,container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 更新图片的颜色
     */
    fun changeImageFilter(path:String,filtername:String) {
        var matrix = AssetsUtils.getColorMatrixByName(filtername)
        var bitmap = FilterUtils.editImgColorMatrix(path,matrix)
        img_preview.setImageBitmap(bitmap!!)
    }
}