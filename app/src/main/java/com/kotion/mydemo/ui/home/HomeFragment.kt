package com.kotion.mydemo.ui.home

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment
import com.kotion.mydemo.R
import com.kotion.mydemo.api.Constants
import com.kotion.mydemo.base.BaseFragment
import com.kotion.mydemo.databinding.FragmentHomeBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.vm.home.HViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment :
    BaseFragment<HViewModel, FragmentHomeBinding>(R.layout.fragment_home, HViewModel::class.java),
    ClickEvt {

    private var tabIndex = 0

    /**
     * 提供的HomeFragment的实例
     */
    companion object {
        val homeFragment: HomeFragment by lazy {
            HomeFragment()
        }
    }

    override fun initView() {
        //默认显示推荐页面
        selectTab(Constants.TAB_RECOMMEND)
    }

    /**
     * 修改选中的tab
     * index 0 1 2 分别表示同城 关注 推荐
     */
    private fun selectTab(index: Int) {
        txt_city.textSize = 20.0f
        txt_interest.textSize = 20.0f
        txt_recommend.textSize = 20.0f
        when (index) {
            Constants.TAB_CITY -> {
                txt_city.textSize = 20.0f
                channelFragment(CityFragment.cityFragment)
            }
            Constants.TAB_INTEREST -> {
                txt_interest.textSize = 20.0f
                channelFragment(InterestFragment.interestFragment)
            }
            Constants.TAB_RECOMMEND -> {
                txt_recommend.textSize = 20.0f
                channelFragment(RecommandFragment.recommandFragment)
            }
        }
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {
        mDataBinding.homeClick=this
    }

    override fun clickListener(v: View) {
        when (v.id) {
            R.id.txt_city -> {
                if (tabIndex != Constants.TAB_CITY) {
                    channelFragment(CityFragment.cityFragment)
                }
            }
            R.id.txt_interest -> {
                if (tabIndex != Constants.TAB_INTEREST) {
                    channelFragment(InterestFragment.interestFragment)
                }
            }
            R.id.txt_recommend -> {
                if (tabIndex != Constants.TAB_RECOMMEND) {
                    channelFragment(RecommandFragment.recommandFragment)
                }
            }
        }
    }

    private fun channelFragment(fragment: Fragment) {
        var trans = fragmentManager!!.beginTransaction()
        trans.replace(R.id.fragmentbox, fragment).commit()
    }


}