package com.kotion.mydemo.vm.more

import com.kotion.mydemo.base.BaseViewModel
import com.kotion.mydemo.net.Inject
import com.kotion.mydemo.net.Repository

class EditImageViewModel :BaseViewModel<Repository>(Inject.repository){
    var currencyIndex:Int =0
    var imgTotal:Int =0
}