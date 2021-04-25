package com.kotion.mydemo.data

class NewsData(
    val `data`: List<Data>,
    val ret: Int
){
    data class Data(
        val content: String,
        val id: Int,
        val source: String,
        val thumbnail: String,
        val title: String,
        val updateDate: String
    )
}

