package com.kotion.mydemo.ui.home

import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.observe
import com.google.android.material.tabs.TabLayout
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseFragment
import com.kotion.mydemo.data.ChannelDataItem
import com.kotion.mydemo.databinding.FragmentRecommandBinding
import com.kotion.mydemo.vm.home.RecommandViewModel
import kotlinx.android.synthetic.main.fragment_recommand.*

class RecommandFragment (
    var commandId:Int
):BaseFragment<RecommandViewModel,FragmentRecommandBinding>(R.layout.fragment_recommand,RecommandViewModel::class.java){
    companion object{
        val recommandFragment:RecommandFragment by lazy {
            RecommandFragment(3)
        }
    }


    /**
     * 当前频道所对应的ftagment
     */
    private var fragments:MutableList<ChannelFragment> = mutableListOf()
    private var tabNames:MutableList<String> = mutableListOf()


    override fun initView() {
        tabLayout.tabMode=TabLayout.MODE_SCROLLABLE
        viewPager.adapter=MyFragmentAdapter(fragmentManager!!)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun initVM() {
        mViewModel.channelData.observe(this,{
            initTabs(it)
        })
    }

    private fun initTabs(list:List<ChannelDataItem>){
        //通过list集合的跌倒器创建tabs
        for (item in list.listIterator()){
            //tab创建
            var tab=tabLayout.newTab()
            tab.setText(item.name)
            tabLayout.addTab(tab)
            tabNames.add(item.name)
            //fragment的创建
            val fragment = ChannelFragment(commandId, item.id)
            fragments.add(fragment)
        }
        viewPager.adapter!!.notifyDataSetChanged()

    }

    override fun initData() {
        mViewModel.getChannel()
    }

    override fun initVariable() {

    }

    inner class MyFragmentAdapter(
        val fm:FragmentManager
    ):FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabNames.get(position)
        }
    }
}