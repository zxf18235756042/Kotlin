package com.kotion.mydemo.ui.more.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.kotion.mydemo.R
import com.kotion.mydemo.data.GridImgData
import com.kotion.mydemo.utils.*
import com.kotion.mydemo.widget.DragLayout
import com.kotion.mydemo.widget.StickerWidget

/**
 * 编辑图片的页面 滤镜
 */
class EditFragment(
       var path:String
):Fragment() {
    private var imgPreview:ImageView? = null
    private var dragLayout:DragLayout? = null

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
        dragLayout = view as DragLayout
        imgPreview = view.findViewById(R.id.img_preview)
        refreshImageUrl(path)

    }


    /**
     * 切换图片刷新
     */
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
    /**
     * 添加图标
     */
    fun addStickerIcon(stickerName:String){
        //添加子组件之前把原来添加的组件的数据保存刷新
        dragLayout!!.saveRefreshParams()
        var widget = StickerWidget(context!!)
        widget.addImg(stickerName)
        dragLayout!!.addChild(widget)
    }

    /**
     * 添加标签
     */
    fun addTags(tagsType:TagsType,tagsId:Int,tagsName:String){
        dragLayout!!.saveRefreshParams()
        var tagsWidget = TagsWidget(context!!,tagsName,tagsType,tagsId)
        tagsWidget.addTagView()
        dragLayout!!.addTagsChild(tagsWidget)
    }

    fun getGridImgData(): GridImgData {
        if (dragLayout==null){
            return  GridImgData(path, arrayListOf())
        }
        var list = dragLayout!!.getImgsDataList()
        var item = GridImgData(path,list)
        return item
    }
}