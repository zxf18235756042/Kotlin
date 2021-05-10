package com.kotion.mydemo.widget



import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.kotion.mydemo.data.GridImgData
import com.kotion.mydemo.utils.TagsType
import com.kotion.mydemo.utils.TagsWidget

class DragLayout: FrameLayout {

    constructor(context: Context) : super(context) {

    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    private var childs:MutableList<StickerWidget> = arrayListOf()

    private var childTags:MutableList<TagsWidget> = arrayListOf()


    /**
     * 添加child对象
     */
    fun addChild(child:StickerWidget){
        childs.add(child)
        addView(child)
    }

    /**
     * 添加tags对象
     */
    fun addTagsChild(child:TagsWidget){
        childTags.add(child)
        addView(child)
    }

    /**
     * 保存刷新属性
     */
    fun saveRefreshParams(){
        for(item in childs){
            item.refreshPosition()
        }
        for(item in childTags){
            item.refreshPosition()
        }
    }

    /**
     * 获取图标标签数据
     */
    fun getImgsDataList():ArrayList<GridImgData.ImgTags>{
        var list = arrayListOf<GridImgData.ImgTags>()
        for(item in childTags){
            //获取当前的标签的数据
            var type = when(item.tagsType){
                TagsType.GOOD -> {2}
                else->{1}
            }
            var tags = GridImgData.ImgTags(
                item.tagsId,
                type,
                item.word,
                item.x.toInt(),
                item.y.toInt()
            )
            list.add(tags)
        }
        return list
    }






}