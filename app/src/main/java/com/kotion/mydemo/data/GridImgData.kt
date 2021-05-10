package com.kotion.mydemo.data

import java.io.Serializable

/**
 * 提交页面图片所对应的数据对象
 */
data class GridImgData(
    var path: String,
    var tags:ArrayList<ImgTags>?
):Serializable{
    //备用的图片上传成功以后的网络地址
    lateinit var netUrl:String
    data class ImgTags(
        var id:Int,
        var type:Int,
        var name: String?,
        var x:Int,
        var y:Int
    ):Serializable
}