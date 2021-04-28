package com.kotion.mydemo.ui.login

import android.util.Log
import android.view.View
import androidx.lifecycle.observe
import com.kotion.mydemo.R
import com.kotion.mydemo.base.BaseActivity
import com.kotion.mydemo.databinding.ActivityLoginBinding
import com.kotion.mydemo.evts.ClickEvt
import com.kotion.mydemo.vm.login.LoginViewModel

class LoginActivity :BaseActivity<LoginViewModel,ActivityLoginBinding>
    (R.layout.activity_login,LoginViewModel::class.java),ClickEvt{
    override fun initView() {

    }

    override fun initVM() {
        mViewModel.loginData.observe(this) {
            Log.i("TAG","登录结果")
            finish()
        }
    }

    override fun initData() {

    }

    /**
     * 对界面的数据绑定的变量赋值
     */
    override fun initVariable() {
        mDataBinding.loginVM = mViewModel
        mDataBinding.loginEvt = this
    }

    override fun clickListener(v: View) {
        when(v.id){
            R.id.btn_login->{
                mViewModel.login()
            }
        }
    }
}