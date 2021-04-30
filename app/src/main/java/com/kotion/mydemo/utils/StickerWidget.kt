package com.kotion.mydemo.utils

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView

class StickerWidget(
        context: Context
):FrameLayout(context),View.OnTouchListener {

    init {
        this.setOnTouchListener(this)
    }

    var w = 80
    var h = 80

    fun addImg(stickerName:String){
        var img = ImageView(context)
        var rid = AssetsUtils.getStickersIconByName(stickerName)
        img.setImageResource(rid)
        img.tag = 1
        var param = LayoutParams(w,h)
        img.layoutParams = param
        addView(img)
    }

    var curView: View? = null    //当前选中的容器
    var startX=0
    var startY=0
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if(v is StickerWidget && (v!!.tag == null || v!!.tag == 1)){
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
                        curView!!.layout(l+dx,t+dy,r+dx,b+dy)
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

}