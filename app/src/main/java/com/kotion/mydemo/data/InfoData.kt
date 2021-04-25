package com.kotion.mydemo.data

class InfoData(
    val `data`: Data,
    val ret: Int
){
    data class Data(
        val article: Article,
        val collect: Boolean
    )

    data class Article(
        val content: String,
        val id: Int,
        val source: String,
        val title: String,
        val updateDate: String
    )
}

