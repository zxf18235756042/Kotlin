package com.kotion.mydemo.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kotion.mydemo.R
import com.kotion.mydemo.widget.StickerWidget

/**
 * type 当前标签的品牌 1 商品 2
 */
class TagsWidget(
        context: Context,
        val word:String,
        val tagsType: TagsType,
        val tagsId:Int
):FrameLayout(context),View.OnTouchListener {

    var lt = 0
    var tp = 0
    var rt = 0
    var bt = 0

    init {
        this.setOnTouchListener(this)
        if(lt != 0 || tp != 0 || rt != 0 || bt != 0){
            layout(lt,tp,rt,bt)
        }
    }

    /**
     * 添加对应的布局内容
     */
    fun addTagView(){
        var view = LayoutInflater.from(context).inflate(R.layout.layout_tags_item,null)
        var param = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        layoutParams = param
        addView(view)
        var imgType = view.findViewById<ImageView>(R.id.img_type)
        var txtWord = view.findViewById<TextView>(R.id.txt_word)
        when(tagsType){
            TagsType.BRAND -> imgType.setImageResource(R.mipmap.icon_vip_crown)
            TagsType.GOOD -> imgType.setImageResource(R.mipmap.icon_wallet)
            else -> imgType.setImageResource(R.mipmap.icon_location)
        }
        if(!word.isNullOrEmpty()){
            txtWord.setText(word)
        }
    }

    var curView: View? = null    //当前选中的容器
    var startX=0
    var startY=0
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if(v is TagsWidget){
            when(event!!.action){
                MotionEvent.ACTION_DOWN -> {
                    curView = v
                    startX = event.getRawX().toInt()
                    startY = event.getRawY().toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    if(curView != null){
                        var x:Int = event.getRawX().toInt()
                        var y:Int = event.getRawY().toInt()
                        var dx = x - startX
                        var dy = y - startY
                        var l = v!!.left
                        var r = v!!.right
                        var t = v!!.top
                        var b = v!!.bottom
                        lt = l+dx
                        tp = t+dy
                        rt = r+dx
                        bt = b+dy
                        curView!!.layout(lt,tp,rt,bt)
                        if(v!!.tag != null){
                            /*var tag = v!!.tag as ImgData.Tag
                            tag!!.x = (l+dx).toFloat()
                            tag!!.y = (t+dy).toFloat()*/
                        }
                    }
                    startX = event.getRawX().toInt()
                    startY = event.getRawY().toInt()
                }
                MotionEvent.ACTION_UP -> {
                    curView = null
                }
            }
        }else{
            curView = null
        }
        return true
    }

    /**
     * 刷新组件位置
     */
    fun refreshPosition(){
        var param:FrameLayout.LayoutParams = layoutParams as LayoutParams? ?: LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        param.setMargins(lt,tp,rt,bt)
        param.width=this.width
        param.height=this.height
        layoutParams = param
    }
}