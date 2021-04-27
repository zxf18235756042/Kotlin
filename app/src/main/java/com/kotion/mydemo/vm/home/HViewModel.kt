package com.kotion.mydemo.vm.home

import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository


/**
 *   author ：H C
 *   time ：2021/4/27
 */
class HViewModel: BaseViewModel<Repository>(Inject.repository) {
}