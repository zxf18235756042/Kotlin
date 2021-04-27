package com.kotion.mydemo.ui.home

import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseFragment
import com.kotion.mydemo.databinding.FragmentChannelBinding
import com.kotion.mydemo.databinding.FragmentCityBinding
import com.kotion.mydemo.vm.home.CitylViewModel


class CityFragment :BaseFragment<CitylViewModel,FragmentCityBinding>(R.layout.fragment_city,CitylViewModel::class.java){
    companion object{
        //获取当前的cityFragent的实例
        val cityFragment:CityFragment by lazy {
            CityFragment()
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
}