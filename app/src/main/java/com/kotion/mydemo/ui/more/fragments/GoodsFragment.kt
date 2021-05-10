package com.kotion.mydemo.ui.more.fragments

import android.view.View
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseFragment
import com.kotion.mydemo.databinding.FragmentBrandBinding
import com.kotion.mydemo.databinding.FragmentGoodBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.vm.more.BrandViewModel
import com.kotion.mydemo.vm.more.GoodsViewModel

class GoodsFragment : BaseFragment<GoodsViewModel, FragmentGoodBinding>(
    R.layout.fragment_good, GoodsViewModel::class.java
), ClickEvt {
    val tagtype=2

    companion object{
        val instance:GoodsFragment by lazy {
            GoodsFragment()
        }
    }
    override fun initView() {

    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }

    override fun clickListener(v: View) {

    }
}