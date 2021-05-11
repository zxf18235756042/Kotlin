package com.kotion.mydemo.widget

import android.app.Activity
import android.os.Bundle
import com.kotion.mydemo.R
import kotlinx.android.synthetic.main.activity_view.*

class ViewsActivity:Activity() {

    private var stageW:Int = 0
    private var stageH:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        initView()
    }

    private fun initView(){
        var display = windowManager.defaultDisplay
        stageW = display.width
        stageH = display.height

        quality_view.initScreenWH(stageW,stageH)
        quality_view.setCurrentValue(350)
        //强制刷新自定义view的绘制功能
        quality_view.invalidate()
    }
}