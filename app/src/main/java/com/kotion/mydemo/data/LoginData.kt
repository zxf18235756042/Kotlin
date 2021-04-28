package com.kotion.mydemo.data

data class LoginData(
    val code: Int,
    val token: String,
    val userInfo: UserInfo
){
    data class UserInfo(
        val age: Any,
        val avater: Any,
        val birthday: Any,
        val nickname: Any,
        val sex: Any,
        val uid: String,
        val username: String
    )
}
