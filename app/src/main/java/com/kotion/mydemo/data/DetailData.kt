package com.kotion.mydemo.data

data class DetailData(
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
