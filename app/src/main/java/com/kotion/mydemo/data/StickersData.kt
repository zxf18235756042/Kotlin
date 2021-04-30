package com.kotion.mydemo.data

data class StickersData(
    val imgs: List<Img>,
    val name: String
){
    data class Img(
        val id: Int,
        val url: String
    )
}

