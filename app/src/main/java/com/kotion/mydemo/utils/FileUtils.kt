package com.kotion.mydemo.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ColorMatrix
import android.os.Environment
import android.view.View
import java.io.*

class FileUtils {
    companion object {
        /**
         * 判断当前的文件是否存在
         */
        fun isExist(path: String):Boolean{
            if(path.isNullOrEmpty()) return false
            return File(path).exists()
        }

        /**
         * 判断SD卡是否挂载
         */
        fun  sDCardAvailable():Boolean{
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
        }


        /**
         * 把对应的布局转换成bitmap
         */
        fun layoutToBitmap(v: View):Bitmap?{
            v.clearFocus()
            v.isPressed = false
            var bool = v.willNotDraw()
            v.setWillNotDraw(false)
            var color = v.drawingCacheBackgroundColor
            v.drawingCacheBackgroundColor = 0
            if(color != 0){
                v.destroyDrawingCache()
            }
            v.buildDrawingCache()
            var cacheBitmap = v.getDrawingCache()
            if(cacheBitmap == null) return null
            var bitmap = Bitmap.createBitmap(cacheBitmap)
            v.destroyDrawingCache()
            v.setWillNotCacheDrawing(bool)
            v.drawingCacheBackgroundColor = color
            return bitmap
        }

        /**
         * 保存bitmap到本地文件
         */
        fun saveBitmapToFile(bitmap: Bitmap, path: String, fileName: String):Boolean{
            if(sDCardAvailable()){
                var file = File(path)
                if(!file.exists()){
                    file.mkdirs()
                }
                file = File(path, fileName + ".png")
                var fileOutputStream:FileOutputStream? = null
                try {
                    fileOutputStream = FileOutputStream(file)
                    if(bitmap != null){
                        if(bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)){
                            fileOutputStream.flush()
                        }
                    }
                }catch (e: FileNotFoundException){
                    file.delete()
                    return false
                }finally {
                    try {
                        fileOutputStream!!.close()
                    }catch (e: IOException){
                        e.printStackTrace()
                        return false
                    }
                }
            }
            return true
        }


        /**
         * 图片的压缩
         * @param path
         * @param width
         * @param height
         * @return
         */
        fun scaleBitmap(path: String?, width: Int, height: Int): Bitmap? {
            if (path == null || path.isEmpty()) return null
            if (!File(path).exists()) return null
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, options)
            // 如果需要输出的大小比原图大，直接返回原图
            if (options.outWidth < width && options.outHeight < height) {
                options.inJustDecodeBounds = false
                return BitmapFactory.decodeFile(path, options)
            }
            val swidth = options.outWidth
            val sheight = options.outHeight
            var size = 1
            while (swidth / size > width || sheight / size > height) {
                size *= 2
            }
            options.inJustDecodeBounds = false
            options.inSampleSize = size
            return BitmapFactory.decodeFile(path, options)
        }

        /**
         * bitmap转字节数组
         * @param bitmap
         * @return
         */
        fun getBytesByBitmap(bitmap: Bitmap): ByteArray {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            return baos.toByteArray()
        }

    }


}