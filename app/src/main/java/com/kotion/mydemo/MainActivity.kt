package com.kotion.mydemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.kotion.mydemo.data.ImgData
import com.kotion.mydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding:ActivityMainBinding?=null
    var username:String? = null
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        context=this
        initView()
    }

    private fun initView() {
        var imgData=ImgData("https://img-baofun.zhhainiao.com/pcwallpaper_ugc/live/2cd0e8ee8b120d5b579878915ac99222.mp4.jpg",1)
        binding!!.imgData=imgData
        binding!!.clickEvt=this
    }

    fun login(){
        Log.i("TAG","login")
    }

    fun register(){
        Log.i("TAG","register")
    }

    fun click(v:View){
        if (v is TextView){
            Log.i("TAG","click is TextView")
        }else  if (v is Button){
            Log.i("TAG","click is Button")
        }
        when(v.id){
            R.id.btn_login->{
                Log.i("TAG","click btn_login")
            }
            R.id.btn_register->{
                var intent = Intent(context!!,HomeActivity::class.java)
                startActivity(intent)
            }
            R.id.txt_username->{
                Log.i("TAG","click txt_username")
            }
        }
    }
}