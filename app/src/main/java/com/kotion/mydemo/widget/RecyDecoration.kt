package com.kotion.mydemo.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyDecoration(
    val offx: Int,
    val offy: Int
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildLayoutPosition(view) %2==0){
            outRect.set(offx,offy,offx,offy)
        }else{
            outRect.set(0,offy,offx,offy)
        }
    }
}