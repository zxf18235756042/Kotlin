package com.kotion.mydemo.api

class Constants {
    companion object{

        var allTrendsGoods:ArrayList<Int> = arrayListOf()

        val TAB_CITY=0//城市
        val TAB_INTEREST=1//关注
        val TAB_RECOMMEND=2//同城

        val TYPE_IMAGE=1    //图片
        val TYPE_VIDEO=2    //视频

        val TAGS_REQUEST=10000
        val TAGS_BRAND=10001   //品牌选择返回
        val TAGS_GOOD=10002    //商品选择返回

        val SUBMIT_REQUEST = 20000

        val SUBMIT_CHANNEL = 20001  //频道选择
        val SUBMIT_THEME = 20002  //主题选择
    }
}