package com.kotion.mydemo.data

/**
 *   author ：H C
 *   time ：2021/4/27
 */
class ChannelData : ArrayList<ChannelDataItem>()

data class ChannelDataItem(
    val id: Int,
    val name: String,
    val sort: Int,
    val visible: Int
)