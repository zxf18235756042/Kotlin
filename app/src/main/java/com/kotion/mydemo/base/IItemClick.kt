package com.kotion.mydemo.base

import android.view.View

interface IItemClick<T> {
    fun clickListener(pos: Int,view: View)
}