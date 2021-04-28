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
    private var currentFragment:Fragment?=null

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
        showFragment(RecommandFragment.recommandFragment)
    }


    /**
     * 切换当前的fragment
     */
    private fun showFragment(fragment: Fragment) {
        if (fragment!=null){
            var trans=fragmentManager!!.beginTransaction()
            if (currentFragment!=null){
                trans.hide(currentFragment!!)
            }
            currentFragment=currentFragment
            //判断当前fargment是否添加到界面
            if (!fragment.isAdded){
                trans.add(R.id.fragmentbox,fragment).show(fragment).commit()
            }else{
                trans.show(fragment).commit()
            }
        }
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
            }
            Constants.TAB_INTEREST -> {
                txt_interest.textSize = 20.0f

            }
            Constants.TAB_RECOMMEND -> {
                txt_recommend.textSize = 20.0f

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
                    showFragment(CityFragment.cityFragment)
                }
            }
            R.id.txt_interest -> {
                if (tabIndex != Constants.TAB_INTEREST) {
                    showFragment(InterestFragment.interestFragment)
                }
            }
            R.id.txt_recommend -> {
                if (tabIndex != Constants.TAB_RECOMMEND) {
                    showFragment(RecommandFragment.recommandFragment)
                }
            }
        }
    }

    private fun channelFragment(fragment: Fragment) {
        var trans = fragmentManager!!.beginTransaction()
        trans.replace(R.id.fragmentbox, fragment).commit()
    }


}