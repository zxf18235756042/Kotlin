package com.kotion.mydemo.utils

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import java.io.File

class FileUtils {
    companion object{
        /**
         * 判断当前的文件是否存在
         */
        fun isExist(path:String): Boolean{
            if (path.isNullOrEmpty()) return false
            return File(path).exists()
        }

    }
}