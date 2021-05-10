package com.kotion.mydemo.ui.more

import android.content.Intent
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseActivity
import com.kotion.mydemo.databinding.ActivityMoreBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.ui.more.adapters.MoreAdapter
import com.kotion.mydemo.vm.more.MoreViewModel
import com.kotion.mydemo.widget.GlideEngine
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_more.*

/**
 *   author ：H C
 *   time ：2021/4/28
 */
class MoreActivity: BaseActivity<MoreViewModel, ActivityMoreBinding>
    (R.layout.activity_more,MoreViewModel::class.java), ClickEvt {

    var files = arrayListOf<String>()
    var moreAdapter: MoreAdapter? = null

    override fun initView() {
        var ids = SparseArray<Int>()
        moreAdapter = MoreAdapter(this,files,ids,this)
        recy_imgs.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recy_imgs.adapter = moreAdapter

        openPhotos()
    }

    private fun openPhotos(){
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
            .maxSelectNum(9)
            .imageSpanCount(3)
            .selectionMode(PictureConfig.MULTIPLE)
            .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PictureConfig.CHOOSE_REQUEST){
            val selectList = PictureSelector.obtainMultipleResult(data)
            if(selectList.size > 0){
                for(item in selectList.iterator()){
                    files.add(item.path) //本地文件的路径
                }
                moreAdapter!!.refreshData(files)
                mViewModel.bigPath=selectList.get(0).path
            }else{
                Log.i("TAG","没有选择任何图片")
            }
        }
    }

    override fun initVM() {

    }

    override fun initData() {

    }

    override fun initVariable() {
        mDataBinding.moreEvt = this

    }

    override fun clickListener(v: View) {
        //下一步
        when(v.id){
            R.id.txt_next ->{
                if (files.size>0){
                    var intent=Intent(this,EditImageActivity::class.java)
                    intent.putStringArrayListExtra("imgs",files)
                    startActivity(intent)
                }else{
                    showTips("请先选择图片")
                }
            }
            R.id.layout_item->{
                var path:String=v.tag as String
                mViewModel.bigPath=path
            }
        }
    }
}