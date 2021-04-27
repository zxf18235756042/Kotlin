package com.kotion.mydemo.ui.home

import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseFragment
import com.kotion.mydemo.databinding.FragmentInterestBinding
import com.kotion.mydemo.vm.home.InterestViewModel


/**
 *   author ：H C
 *   time ：2021/4/27
 */
class InterestFragment: BaseFragment<InterestViewModel, FragmentInterestBinding>(R.layout.fragment_interest,InterestViewModel::class.java) {

    companion object{
        val interestFragment:InterestFragment by lazy {
            InterestFragment()
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