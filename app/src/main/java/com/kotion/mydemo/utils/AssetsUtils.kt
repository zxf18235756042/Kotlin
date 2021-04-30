package com.kotion.mydemo.utils

import android.content.Context
import android.graphics.ColorMatrix
import com.kotion.mydemo.BR
import com.kotion.mydemo.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class AssetsUtils {

    companion object {


            /**
             * 加载assets中的文本文件
             */
            fun loadAssetsFileByName(fileName: String, context: Context): String {
                try {
                    var inputStream = context.getAssets().open(fileName);
                    var lenght = inputStream.available();
                    val inputString = BufferedReader(InputStreamReader(inputStream)).useLines { lines ->
                        val results = StringBuilder()
                        lines.forEach { results.append(it) }
                        results.toString()
                    }
                    return inputString
                } catch (e: IOException) {

                }
                return ""
            }

            /**
             * 通过资源文件的名字获取对应的R.id
             */
            fun getResIdByName(name: String): Int {
                if ("heart0".equals(name)) {
                    return R.mipmap.heart0
                } else if ("heart1".equals(name)) {
                    return R.mipmap.heart1
                } else if ("heart2".equals(name)) {
                    return R.mipmap.heart2
                } else if ("heart3".equals(name)) {
                    return R.mipmap.heart3
                } else if ("heart4".equals(name)) {
                    return R.mipmap.heart4
                }
                return 0
            }

            /**
             * 通过name映射得到图片的滤镜
             */
            fun getColorMatrixByName(name: String): ColorMatrix {
                var matrix = ColorMatrix()
                if ("heart0".equals(name)) {
                    //增强的效果
                    matrix.set(
                        floatArrayOf(
                            1f, 0f, 0f, 0f, 0f,
                            0f, 1f, 0f, 0f, 100f,
                            0f, 0f, 1f, 0f, 0f,
                            0f, 0f, 0f, 1f, 0f
                        )
                    )
                } else if ("heart1".equals(name)) {
                    matrix.set(
                        floatArrayOf(
                            -1f, 0f, 0f, 0f, 255f,
                            0f, -1f, 0f, 0f, 255f,
                            0f, 0f, -1f, 0f, 255f,
                            0f, 0f, 0f, 1f, 0f
                        )
                    )
                } else if ("heart2".equals(name)) {
                    matrix.set(
                        floatArrayOf(
                            0.213f, 0.715f, 0.072f, 0f, 0f,
                            0.213f, 0.715f, 0.072f, 0f, 0f,
                            0.213f, 0.715f, 0.072f, 0f, 0f,
                            0f, 0f, 0f, 1f, 0f
                        )
                    )
                } else if ("heart3".equals(name)) {
                    matrix.set(
                        floatArrayOf(
                            0f, 1f, 0f, 0f, 0f,
                            1f, 0f, 0f, 0f, 0f,
                            0f, 0f, 1f, 0f, 0f,
                            0f, 0f, 0f, 1f, 0f
                        )
                    )
                } else if ("heart4".equals(name)) {
                    matrix.set(
                        floatArrayOf(
                            1 / 2f, 1 / 2f, 1 / 2f, 0f, 0f,
                            1 / 3f, 1 / 3f, 1 / 3f, 0f, 0f,
                            1 / 4f, 1 / 4f, 1 / 4f, 0f, 0f,
                            0f, 0f, 0f, 1f, 0f
                        )
                    )
                }

                return matrix
            }

        }
}