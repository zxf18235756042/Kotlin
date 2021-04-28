
package com.kotion.mydemo.utils

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager


/**
 *   author ：H C
 *   time ：2021/4/28
 */
class SystemUtils {

    companion object{
        /**
         * 获取设备唯一识别编号
         */
        fun getIMEI(context: Context): String? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ""
            } else try {
                val telephonyManager =
                    context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                        ?: return null
                telephonyManager.deviceId
            } catch (e: Exception) {
                ""
            }
        }
    }
}