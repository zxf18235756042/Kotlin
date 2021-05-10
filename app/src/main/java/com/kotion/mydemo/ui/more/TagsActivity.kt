package com.kotion.mydemo.ui.more

import android.view.ViewParent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseActivity
import com.kotion.mydemo.databinding.ActivityTagsBinding
import com.kotion.mydemo.ui.more.fragments.BrandFragment
import com.kotion.mydemo.ui.more.fragments.GoodsFragment
import com.kotion.mydemo.vm.more.GoodsViewModel
import kotlinx.android.synthetic.main.activity_tags.*

class TagsActivity : BaseActivity<GoodsViewModel, ActivityTagsBinding>(
    R.layout.activity_tags, GoodsViewModel::class.java
) {
    //初始化fragment集合
    var fragments= arrayListOf<Fragment>()
    var title = arrayListOf<String>()

    override fun initView() {
        fragments.add(BrandFragment.instance)
        fragments.add(GoodsFragment.instance)
        title.add("品牌")
        title.add("商品")
        tab_tags.addTab(tab_tags.newTab().setText("品牌"))
        tab_tags.addTab(tab_tags.newTab().setText("商品"))
        tab_tags.setupWithViewPager(viewPager_tags)
        viewPager_tags.adapter = TagsFragmentAdapter(supportFragmentManager)
        viewPager_tags.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }

        })
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {

    }

    inner class TagsFragmentAdapter(
        fm:FragmentManager
        ):FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return title.get(position).toString()
        }
    }
}