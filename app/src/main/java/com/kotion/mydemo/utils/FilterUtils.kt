package com.kotion.mydemo.utils

import android.graphics.*


class FilterUtils {
    companion object{
        /**
         * 图片矩阵转换
         */
        fun editImgColorMatrix(path:String,matrix: ColorMatrix):Bitmap?{
            if (!path.isNullOrEmpty()){
                if (FileUtils.isExist(path)){
                    val bitmap=BitmapFactory.decodeFile(path)
                    var bgBp=Bitmap.createBitmap(bitmap.width,bitmap.height,Bitmap.Config.RGB_565)
                    val cancas=Canvas(bgBp)
                    val paint =Paint()
                    paint.setAntiAlias(true)
                    val filter=ColorMatrixColorFilter(matrix)
                    paint.colorFilter=filter
                    cancas.drawBitmap(bitmap,0f,0f,paint)
                    return bgBp
                }
            }
            return null
        }

    }
}