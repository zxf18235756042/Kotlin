package com.kotion.mydemo.ui.more.fragments

import android.content.Intent
import android.provider.SyncStateContract
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotion.mydemo.BR
import com.kotion.mydemo.R
import com.kotion.mydemo.api.Constants
import com.kotion.mydemo.base.BaseFragment
import com.kotion.mydemo.data.BrandData
import com.kotion.mydemo.databinding.FragmentBrandBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.ui.more.adapters.BrandAdapter
import com.kotion.mydemo.vm.more.BrandViewModel
import kotlinx.android.synthetic.main.fragment_brand.*
import org.jetbrains.anko.doAsync


class BrandFragment : BaseFragment<BrandViewModel, FragmentBrandBinding>(
    R.layout.fragment_brand, BrandViewModel::class.java
), ClickEvt {
    val tagtype = 1

    companion object {
        val instance: BrandFragment by lazy {
            BrandFragment()
        }
    }

    lateinit var brandAdapter: BrandAdapter
    var brandList = arrayListOf<BrandData.Data>()

    override fun initView() {
        var layoutTypes: SparseArray<Int> = SparseArray()
        layoutTypes.append(R.layout.layout_brand_item, BR.brandItem)
        brandAdapter = BrandAdapter(mContext, brandList, layoutTypes, this)
        recy_brand.layoutManager = LinearLayoutManager(mContext)
        recy_brand.adapter = brandAdapter
    }

    override fun initVM() {
        mViewModel.brand.observe(this, {
            //brandAdapter.refreshData(it)
            Log.i("TAG", "data:" + it)
            brandAdapter.refreshData(it.data as ArrayList<BrandData.Data>)
        })
    }

    override fun initData() {
        mViewModel.getBrands()
    }

    override fun initVariable() {
    }

    override fun clickListener(v: View) {
        var data: BrandData.Data = v.tag as BrandData.Data
        var intent = Intent()
        intent.putExtra("tagid", data.id)
        intent.putExtra("tagname", data.name)
        mActivity.setResult(Constants.TAGS_BRAND, intent)
        mActivity.finish()
    }
}