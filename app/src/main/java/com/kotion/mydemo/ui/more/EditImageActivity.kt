package com.kotion.mydemo.ui.more

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.kotion.mydemo.R
import com.kotion.mydemo.api.Constants
import com.kotion.mydemo.base.BaseActivity

import com.kotion.mydemo.data.FilterData
import com.kotion.mydemo.data.GridImgData
import com.kotion.mydemo.data.StickersData
import com.kotion.mydemo.databinding.ActivityEditimagBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.ui.more.adapters.FilterAdapter
import com.kotion.mydemo.ui.more.adapters.StickersAdapter
import com.kotion.mydemo.ui.more.fragments.EditFilterFragment
import com.kotion.mydemo.ui.more.fragments.EditFragment
import com.kotion.mydemo.ui.more.fragments.EditStickersFragment
import com.kotion.mydemo.utils.AssetsUtils
import com.kotion.mydemo.utils.TagsType
import com.kotion.mydemo.vm.more.EditImageViewModel
import kotlinx.android.synthetic.main.activity_editimag.*
import org.json.JSONArray

class EditImageActivity : BaseActivity<EditImageViewModel, ActivityEditimagBinding>
    (R.layout.activity_editimag, EditImageViewModel::class.java), ClickEvt {
    private var fragments = ArrayList<Fragment>()
    lateinit var fragmentFmAdapter:EditFmAdatper

    private var filters = arrayListOf<FilterData>()
    //sticker 贴图的tab数据
    private var stickers = arrayListOf<StickersData>()
    private var stickersItems = arrayListOf<StickersData.Img>()


    lateinit var filterAdapter:FilterAdapter
    lateinit var stickerAdapter:StickersAdapter

    private var imgs:List<String> = arrayListOf()

    private var currentFragmentIndex = 0

    override fun initView() {
        fragmentFmAdapter = EditFmAdatper(supportFragmentManager)
        imgVP.adapter = fragmentFmAdapter
        imgVP.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                currentFragmentIndex = position
                (fragments.get(currentFragmentIndex) as EditFragment).refreshImageUrl(imgs.get(position))
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    override fun initVM() {

    }

    override fun initData() {
        imgs = intent.getStringArrayListExtra("imgs")!!
        if(imgs != null && imgs.size > 0){
            for(i in 0..imgs.size-1){
                fragments.add(EditFragment(imgs.get(i)))
            }
            fragmentFmAdapter.notifyDataSetChanged()
            mViewModel.currencyIndex = 1
            mViewModel.imgTotal = imgs.size
            initTab()
            initFilter()
            initStickers()

            recy_filter.visibility = View.VISIBLE
            layout_stickers.visibility = View.GONE

        }else{
            mViewModel.currencyIndex = 0
            mViewModel.imgTotal = 0
        }
    }

    private fun initTab(){
        layout_tab.addTab(layout_tab.newTab().setText("滤镜"))
        layout_tab.addTab(layout_tab.newTab().setText("贴纸"))
        layout_tab.addTab(layout_tab.newTab().setText("标签"))
        layout_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if("滤镜".equals(tab!!.text.toString())){
                    recy_filter.visibility = View.VISIBLE
                    layout_stickers.visibility = View.GONE
                }else if("贴纸".equals(tab!!.text.toString())){
                    layout_stickers.visibility = View.VISIBLE
                    recy_filter.visibility = View.GONE
                }else if("标签".equals(tab!!.text.toString())){
                    //
                    var intent = Intent(mContext,TagsActivity::class.java)
                    startActivityForResult(intent,Constants.TAGS_REQUEST)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    /**
     * 用来保存当前activity界面的值
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Constants.TAGS_REQUEST){
            when(resultCode){
                Constants.TAGS_BRAND -> {  // type = 1
                    if(data != null){
                        var tagid = data.getIntExtra("tagid",0)
                        var tagname = data.getStringExtra("tagname")
                        (fragments.get(currentFragmentIndex) as EditFragment).addTags(TagsType.BRAND,tagid,tagname!!)
                    }else{
                        showTips("没有返回对应的数据")
                    }
                }
                Constants.TAGS_GOOD -> {  // type = 2
                    if(data != null){
                        var tagid = data.getIntExtra("tagid",0)
                        var tagname = data.getStringExtra("tagname")
                        (fragments.get(currentFragmentIndex) as EditFragment).addTags(TagsType.GOOD,tagid,tagname!!)
                    }
                }
            }
        }
    }

    /**
     * 滤镜列表的初始化
     */
    private fun initFilter(){
        var filterContent = AssetsUtils.loadAssetsFileByName("filter.json",mContext)
        if(!filterContent.isNullOrEmpty()){
            var jsonArray = JSONArray(filterContent)  // json数组
            for(i in 0..jsonArray.length()-1){
                var filterData = Gson().fromJson<FilterData>(jsonArray.getString(i),FilterData::class.java)
                filters.add(filterData)
            }
        }
        var layoutTypes = SparseArray<Int>()
        filterAdapter = FilterAdapter(mContext,filters,layoutTypes,this)
        recy_filter.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)
        recy_filter.adapter = filterAdapter
    }

    /**
     * 贴纸列表的初始化
     */
    private fun initStickers(){
        var stickerContent = AssetsUtils.loadAssetsFileByName("stickers.json",mContext)
        if(!stickerContent.isNullOrEmpty()){
            var jsonArray = JSONArray(stickerContent)  // json数组
            for(i in 0..jsonArray.length()-1){
                var stickerData = Gson().fromJson<StickersData>(jsonArray.getString(i),StickersData::class.java)
                stickers.add(stickerData)
                //初始化对应的tablayout
                tab_stickers.addTab(tab_stickers.newTab().setText(stickerData.name))
                if(i == 0){
                    stickersItems.addAll(stickerData.imgs)
                }
            }
        }
        var layoutTypes = SparseArray<Int>()
        stickerAdapter = StickersAdapter(mContext,stickersItems,layoutTypes,this)
        recy_stickers.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)
        recy_stickers.adapter = stickerAdapter

        tab_stickers.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var pos = tab!!.position
                if(pos < stickers.size){
                    stickersItems.clear()
                    stickersItems.addAll(stickers.get(pos).imgs)
                    stickerAdapter.refreshData(stickersItems)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun initVariable() {
        mDataBinding.editImgVM = mViewModel
        mDataBinding.editImgEvt = this
    }

    inner class EditFmAdatper(
        fragmentManager: FragmentManager
    ):FragmentPagerAdapter(fragmentManager){
        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            /*return when (position) {
                0 -> EditFilterFragment.instance
                else -> EditStickersFragment.instance
            }*/
            return fragments.get(position)
        }

    }

    override fun clickListener(v: View) {
        when(v.id){
            R.id.layout_filterItem -> {
                var data:FilterData = v.tag as FilterData
                if(data != null){
                    (fragments.get(currentFragmentIndex) as EditFragment).changeImageFilter(data.icon)
                }
            }
            R.id.img_sticker -> {
                var data:StickersData.Img = v.tag as StickersData.Img
                if(data != null){
                    (fragments.get(currentFragmentIndex) as EditFragment).addStickerIcon(data.url)
                }
            }
            R.id.ed_next    -> {  //编辑页面的下一步 - 提交页面
                gotoSubmitActivity()
            }
        }
    }

    private fun gotoSubmitActivity(){
        var intent = Intent(this,SubmitActivity::class.java)
        var list:ArrayList<GridImgData> = getImgs()
        //var data = EditImgData(list)
        //intent.putExtra("imgs",data)
        intent.putExtra("imgs",list)
        startActivity(intent)
    }

    private fun getImgs():ArrayList<GridImgData>{
        var list  = arrayListOf<GridImgData>()
        for(item in fragments){
            var fragment = item as EditFragment
            list.add(fragment.getGridImgData())
        }
        return list
    }
}