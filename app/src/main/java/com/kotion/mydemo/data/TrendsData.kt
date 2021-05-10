package com.kotion.mydemo.data

/**
 *   author ：H C
 *   time ：2021/4/27
 */
class TrendsData : ArrayList<TrendsDataItem>()

data class TrendsDataItem(
    val address: String,
    val avater: Any,
    val channelid: Int,
    val date: String,
    var goods: Int,
    val id: Int,
    val lat: Int,
    val lng: Int,
    val mood: String,
    val nickname: String,
    val res: List<Re>,
    val themeid: Int,
    val title: String,
    val type: Int,
    val uid: String,
    val url: String
){
    data class Re(
        val tags: List<Tag>,
        val url: String
    )

    data class Tag(
        val lat: Int,
        val lng: Int,
        val resid: Int,
        val tag_x: Int,
        val tag_y: Int,
        val tagid: Int,
        val tagname: String,
        val tagtype: Int
    )
}

