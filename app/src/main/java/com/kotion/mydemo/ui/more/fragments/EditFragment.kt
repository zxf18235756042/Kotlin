package com.kotion.mydemo.ui.more.fragments

import android.graphics.ColorMatrix
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.kotion.mydemo.R
import com.kotion.mydemo.utils.AssetsUtils
import com.kotion.mydemo.utils.FilterUtils
import kotlinx.android.synthetic.main.fragment_editfilter.*
import java.io.File
import java.net.URI

/**
 * 编辑图片的页面 滤镜
 */
class EditFragment(
       var path:String
):Fragment() {

    private var imgPreview:ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_editfilter,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgPreview = view.findViewById(R.id.img_preview)
        refreshImageUrl(path)

    }

    fun refreshImageUrl(path:String){
        imgPreview!!.setImageURI(Uri.parse(path))
    }

    /**
     * 更新图片的颜色
     */
    fun changeImageFilter(filtername:String){
        var matrix = AssetsUtils.getColorMatrixByName(filtername)
        var bitmap = FilterUtils.editImgColorMatrix(path,matrix)
        imgPreview!!.setImageBitmap(bitmap!!)
    }
}