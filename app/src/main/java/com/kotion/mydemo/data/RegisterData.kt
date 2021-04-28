package com.kotion.mydemo.data


data class RegisterData(
    val token: String,
    val userInfo: UserInfo
)
{
    data class UserInfo(
        val avatar: String,
        val birthday: Int,
        val gender: Int,
        val nickname: Any,
        val uid: String,
        val username: String
    )
}
