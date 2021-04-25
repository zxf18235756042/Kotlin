package com.kotion.mydemo.net

object Inject {
    val repository:Repository by lazy {
        Repository()
    }
}