package com.kotion.mydemo.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding>(
    val layoutId: Int,
    var vmClass: Class<VM>
)