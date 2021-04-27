package com.kotion.mydemo.vm

import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository

class MainViewModel : BaseViewModel<Repository>(Inject.repository){
}