package com.kotion.mydemo.ui.more

import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.sdk.android.oss.ClientConfiguration
import com.alibaba.sdk.android.oss.ClientException
import com.alibaba.sdk.android.oss.OSSClient
import com.alibaba.sdk.android.oss.ServiceException
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.kotion.mydemo.BR
import com.kotion.mydemo.MainActivity
import com.kotion.mydemo.R
import com.kotion.mydemo.api.Constants
import com.kotion.mydemo.base.BaseActivity
import com.kotion.mydemo.data.GridImgData
import com.kotion.mydemo.databinding.ActivitySubmitBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.ui.more.adapters.SubmitImgAdapter
import com.kotion.mydemo.utils.FileUtils
import com.kotion.mydemo.vm.more.SubmitViewModel
import com.kotion.mydemo.widget.GlideEngine
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.shop.utils.MyMmkv
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.layout_submit_img.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject


class SubmitActivity:
    BaseActivity<SubmitViewModel, ActivitySubmitBinding>(R.layout.activity_submit,SubmitViewModel::class.java),
    ClickEvt {

    var imgs = arrayListOf<GridImgData>()
    lateinit var imgAdapter:SubmitImgAdapter

    var channelid = 0  //频道的ID
    var themeid = 0  //主题的ID
    var type = 1 // 1图片 2视频

    var bucketName = "sprout-app"
    var ossPoint = "http://oss-cn-beijing.aliyuncs.com"
    var key = "LTAI5t7afAVVFZq84X6puaqa" //appkey
    var secret = "K4t5BRAiSttIk9HNR8D1z1itxb1oiR" //密码
    lateinit var ossClient: OSSClient //ossclient对象

    var updateImgList:ArrayList<String> = arrayListOf()


    override fun initView() {

        //加号
        imgs.add(GridImgData("",null))

        var layoutTypes = SparseArray<Int>()
        layoutTypes.append(R.layout.layout_submit_img,BR.gridImg)
        imgAdapter = SubmitImgAdapter(mContext,imgs,layoutTypes,object:ClickEvt{
            override fun clickListener(v: View) {
                openPhotos()
            }

        })

        recy_imgs.layoutManager = GridLayoutManager(this,3)
        recy_imgs.adapter = imgAdapter

        initOss()
    }

    override fun initVM() {

        //监听动态数据提交的结果
        mViewModel.submitResult.observe(this,{
            //动态提交成功，回到主页面
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })


    }

    override fun initData() {
        if(intent.hasExtra("imgs")){
            var data:ArrayList<GridImgData> = intent.getSerializableExtra("imgs") as ArrayList<GridImgData>
            if(data != null){  //编辑页面进入
                Log.i("TAG","Data:"+data.toString())
                if(data.size >= 9){
                    imgs.clear()
                    imgs.addAll(data)
                }else{
                    imgs.addAll(0,data)
                }
                imgAdapter.notifyDataSetChanged()
            }
        }else{
            var content = MyMmkv.getString("trends_prev")
            if(!content.isNullOrEmpty()){
                //解析草稿的数据内容
                /*var prevData = Gson().fromJson<PrevData>(content,PrevData::class.java)
                if(!prevData.title.isNullOrEmpty()){
                    edit_title.setText(prevData.title)
                }
                if(!prevData.content.isNullOrEmpty()){
                    edit_content.setText(prevData.content)
                }*/
                var obj = JSONObject(content)
                var title = obj.getString("title")
                if(!title.isNullOrEmpty()){
                    edit_title.setText(title)
                }
                var content = obj.getString("content")
                if(!content.isNullOrEmpty()){
                    edit_content.setText(content)
                }
                var imgsStr = obj.getString("imgs") // []
                var jsArray = JSONArray(imgsStr)
                var list = arrayListOf<GridImgData>()
                if(jsArray.length() > 0){
                    for(i in 0..jsArray.length()-1){
                        var itemStr = jsArray.getString(i)
                        var img = Gson().fromJson<GridImgData>(itemStr,GridImgData::class.java)
                        list.add(img)
                    }
                }
                if(list.size >= 9){
                    imgs.clear()
                    imgs.addAll(list)
                }else{
                    imgs.addAll(0,list)
                }
                imgAdapter.notifyDataSetChanged()

            }
        }
    }

    override fun initVariable() {
        mDataBinding.submitEvt = this
    }


    /**
     * 初始化oss
     */
    private fun initOss() {
        val credentialProvider: OSSCredentialProvider = OSSStsTokenCredentialProvider(
            key,
            secret,
            ""
        )
        // 配置类如果不设置，会有默认配置。
        val conf = ClientConfiguration()
        conf.connectionTimeout = 15 * 1000 // 连接超时，默认15秒。
        conf.socketTimeout = 15 * 1000 // socket超时，默认15秒。
        conf.maxConcurrentRequest = 5 // 最大并发请求数，默认5个。
        conf.maxErrorRetry = 2 // 失败后最大重试次数，默认2次。
        ossClient = OSSClient(applicationContext, ossPoint, credentialProvider)
    }

    private fun openPhotos(){
        // 剩下还能选择的图片数量
        var num = 9-imgs.size+1
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
            .maxSelectNum(num)
            .imageSpanCount(3)
            .selectionMode(PictureConfig.MULTIPLE)
            .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PictureConfig.CHOOSE_REQUEST){
            val selectList = PictureSelector.obtainMultipleResult(data)
            if(selectList.size > 0){
                // 处理越界
                imgs.removeAt(imgs.size - 1)
                for(item in selectList.iterator()){
                    var imgItem = GridImgData(item.path, arrayListOf())
                    imgs.add(imgItem)
                }
                //添加完图片还是小于9张
                if(imgs.size < 9){
                    imgs.add(GridImgData("",null))
                }
                imgAdapter.notifyDataSetChanged()
            }else{
                Log.i("TAG","没有选择任何图片")
            }
        }else if(requestCode == Constants.SUBMIT_REQUEST){
            when(resultCode){
                Constants.SUBMIT_CHANNEL -> {
                    if(data != null && data.hasExtra("channelid")){
                        channelid = data.getIntExtra("channelid",0)
                    }else{
                        showTips("请选择频道")
                    }
                }
                Constants.SUBMIT_THEME -> {
                    if(data != null && data.hasExtra("themeid")){
                        themeid = data.getIntExtra("themeid",0)
                    }else{
                        showTips("请选择主题")
                    }
                }
            }
        }
    }

    override fun clickListener(v: View) {
        when(v.id){
            R.id.txt_prev -> {
                //村草稿
                saveLocalData()
            }
            R.id.txt_submit -> {
                if(edit_title.text.toString().isNullOrEmpty()){
                    showTips("请输入标题")
                    return
                }
                if(edit_content.text.toString().isNullOrEmpty()){
                    showTips("请输入内容")
                    return
                }
                if(channelid == 0){
                    showTips("请输入频道")
                    return
                }
                if(themeid == 0){
                    showTips("请选择主题")
                    return
                }
                if(imgs.size < 2){
                    showTips("请选择图片")
                    return
                }
                //动态数据的额提交
                submit()
            }
            R.id.txt_channel -> {
                var intent = Intent(this,ChannelActivity::class.java)
                startActivityForResult(intent,Constants.SUBMIT_REQUEST)
            }
            R.id.txt_theme -> {

            }
        }
    }

    private fun saveLocalData(){
        var content = encodeDataToJson()
        MyMmkv.setValue("trends_prev",content)
        Log.i("TAG",content)
    }

    /**
     * 界面数据转json字符串
     */
    private fun encodeDataToJson():String{
        var jsObject = JSONObject()  //最外层的json结构 {}
        jsObject.put("title",edit_title.text.toString())
        jsObject.put("content",edit_content.text.toString())
        var jsArr = JSONArray() // []
        for(item in imgs){
            if(item.path.isNullOrEmpty()) continue
            var jsItem = JSONObject() // {}
            jsItem.put("path",item.path)
            if(item.tags != null){
                var jsTagsArr = JSONArray() // []
                for(tag in item.tags!!){
                    var jsTagItem = JSONObject()
                    jsTagItem.put("id",tag.id)
                    jsTagItem.put("type",tag.type)
                    jsTagItem.put("name",tag.name)
                    jsTagItem.put("x",tag.x)
                    jsTagItem.put("y",tag.y)
                    jsTagsArr.put(jsTagItem)
                }
                jsItem.put("tags",jsTagsArr)
            }
            jsArr.put(jsItem)
        }
        jsObject.put("imgs",jsArr)
        return jsObject.toString()
    }

    /**
     * 封装一个发布动态的数据结构
     */
    private fun encodeSubmitJson():String{
        var jsObject = JSONObject()
        //结构的第一层
        jsObject.put("type",1)
        jsObject.put("title",edit_title.text.toString())
        jsObject.put("mood",edit_content.text.toString())
        jsObject.put("address","北京")
        jsObject.put("themeid",themeid)
        jsObject.put("channelid",channelid)
        jsObject.put("lng",0)
        jsObject.put("lat",0)
        //结构的第二层  res
        var jsArray = JSONArray()
        for(item in imgs){
            if(item.path.isNullOrEmpty() || item.netUrl.isNullOrEmpty()) continue
            //第三层结构
            var jsImgObj = JSONObject()
            jsImgObj.put("url",item.netUrl)
            //第四层结构
            var jsTagsArr = JSONArray()
            for(tag in item.tags!!){
                //第5层
                var jsTag = JSONObject()
                jsTag.put("type",tag.type)
                jsTag.put("id",tag.id)
                jsTag.put("name",tag.name)
                jsTag.put("x",tag.x)
                jsTag.put("y",tag.y)
                jsTagsArr.put(jsTag)
            }
            jsImgObj.put("tags",jsTagsArr)
            jsArray.put(jsImgObj)
        }
        jsObject.put("res",jsArray)
        return jsObject.toString()
    }

    /**
     * 发布动态
     */
    private fun submit(){
        updateImgList.clear()
        getUploadImgs()
        if(updateImgList.size > 0){
            GlobalScope.launch(Dispatchers.Unconfined){
                for(item in updateImgList){
                    uploadImg(item)
                }
            }
        }
    }

    /**
     * 提交发布动态的数据
     */
    private fun submitData(){
        var data = encodeSubmitJson()
        Log.i("TAG",data)
        mViewModel.submit(data)
    }

    /**
     * 获取当前需要上传的图片路径
     */
    private fun getUploadImgs(){
        for(item in imgs){
            if(!item.path.isNullOrEmpty()){
                updateImgList.add(item.path)
            }
        }
    }

    //上传图片协程
    suspend fun uploadImg(path: String){
        var bitmap = BitmapFactory.decodeFile(path)
        // 上传图片
        val bytes: ByteArray = FileUtils.getBytesByBitmap(bitmap)
        //设置oss服务上的文件夹
        val uid: String = MyMmkv.getString("uid")!!
        val imgName =  System.currentTimeMillis() + Math.random() * 10000
        val fileName = uid + "/" + imgName + ".png"
        val put = PutObjectRequest(bucketName, fileName, bytes)
        put.setProgressCallback(object : OSSProgressCallback<PutObjectRequest> {
            override fun onProgress(
                request: PutObjectRequest?,
                currentSize: Long,
                totalSize: Long
            ) {
                //上传进度
                Log.i("oss_upload", "$currentSize/$totalSize")
            }

        })

        val task: OSSAsyncTask<*> = ossClient.asyncPutObject(
            put,
            object : OSSCompletedCallback<PutObjectRequest, PutObjectResult> {
                override fun onSuccess(request: PutObjectRequest, result: PutObjectResult) {
                    Log.d("PutObject", "UploadSuccess")
                    Log.d("ETag", result.eTag)
                    Log.d("RequestId", result.requestId)
                    //成功的回调中读取相关的上传文件的信息  生成一个url地址
                    val url = ossClient.presignPublicObjectURL(
                        request.bucketName,
                        request.objectKey
                    )
                    //把上传成功的url地址更新到imgs中GridImgData
                    updateImgsNetUrl(path,url)
                    //把上传成功的图片进行清理
                    checkUpdateImgList(path)
                    //图片上传是否结束
                    if(isUpLoadImgOver()){
                        submitData()
                    }
                }

                override fun onFailure(
                    request: PutObjectRequest,
                    clientExcepion: ClientException,
                    serviceException: ServiceException
                ) {
                    checkUpdateImgList(path)
                    if(isUpLoadImgOver()){
                        submitData()
                    }
                    // 请求异常。
                    if (clientExcepion != null) {
                        // 本地异常，如网络异常等。
                        clientExcepion.printStackTrace()
                    }
                    if (serviceException != null) {
                        // 服务异常。
                        Log.e("ErrorCode", serviceException.errorCode)
                        Log.e("RequestId", serviceException.requestId)
                        Log.e("HostId", serviceException.hostId)
                        Log.e("RawMessage", serviceException.rawMessage)
                    }
                }
            })
    }

    /**
     * 更新imgs中的图片的上传成功得到的地址
     */
    private fun updateImgsNetUrl(path:String,url:String){
        for(item in imgs){
            if(!item.path.isNullOrEmpty() && item.path.equals(path)){
                item.netUrl = url
                break
            }
        }
    }

    /**
     * 清理掉已经上传成功的图片路径
     */
    private fun checkUpdateImgList(path:String){
        for(item in updateImgList){
            if(path.equals(item)){
                updateImgList.remove(item)
                return
            }
        }
    }

    /**
     * 判断当前的图片是否上传完成
     */
    private fun isUpLoadImgOver():Boolean{
        return if(updateImgList.size == 0) true else false
    }



}