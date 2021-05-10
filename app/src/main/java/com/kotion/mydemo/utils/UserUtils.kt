package com.kotion.mydemo.utils

import com.kotion.mydemo.api.Constants


class UserUtils {

    companion object{

        fun isGoods(trendsid:Int):Boolean{
            for(item in Constants.allTrendsGoods){
                if(trendsid == item){
                    return true
                }
            }
            return false
        }

    }

}

