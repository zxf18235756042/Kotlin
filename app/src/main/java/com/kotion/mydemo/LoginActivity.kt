package com.kotion.mydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var username:String
    private lateinit var pw:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        btn_login.setOnClickListener { v->
            login()
        }

    }

    private fun login(){
        username=input_username.text.toString()
        pw=input_pw.text.toString()
        if (!username.isNotEmpty() and !pw.isNotEmpty()){
          //  println("this is name: $username and pw:$pw")
        }
    }


}