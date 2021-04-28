package com.kotion.mydemo.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.kotion.mydemo.api.MyApp
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


/**
 *   author ：H C
 *   time ：2021/4/28
 */
class LocationUtils {
    companion object{
        private var locationManager: LocationManager? = null
        private var mContext: Context? = null
        private var addressCallbacks: ArrayList<AddressCallback>? = null
        private var addressCallback: AddressCallback? = null



        fun getAddressCallback(): AddressCallback? {
            return addressCallback
        }

        fun setAddressCallback(addressCallback: AddressCallback?) {
            this.addressCallback = addressCallback
            if (isInit) {
                showLocation()
            } else {
                isInit = true
            }
        }

        private var location: Location? = null
        private var isInit = false //是否加载过

        init {
            mContext = MyApp.app
            addressCallbacks = ArrayList()
            getLocation()
        }

        /**
         * 添加回调事件
         * @param addressCallback
         */
        private fun addAddressCallback(addressCallback: AddressCallback) {
            addressCallbacks!!.add(addressCallback)
            if (isInit) {
                showLocation()
            }
        }

        /**
         * 移除回调事件
         * @param addressCallback
         */
        fun removeAddressCallback(addressCallback: AddressCallback?) {
            if (addressCallbacks!!.contains(addressCallback)) {
                addressCallbacks!!.remove(addressCallback)
            }
        }

        /**
         * 清空回调事件
         */
        fun cleareAddressCallback() {
            removeLocationUpdatesListener()
            addressCallbacks!!.clear()
        }

        private fun getLocation() {
            //1.获取位置管理器
            locationManager =
                mContext!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
            //添加用户权限申请判断
            if (ActivityCompat.checkSelfPermission(
                    mContext!!,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    mContext!!,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            //2.获取位置提供器，GPS或是NetWork
            // 获取所有可用的位置提供器
            val providerList =
                locationManager!!.getProviders(true)
            val locationProvider: String
            if (providerList.contains(LocationManager.GPS_PROVIDER)) {
                //GPS 定位的精准度比较高，但是非常耗电。
                println("=====GPS_PROVIDER=====")
                locationProvider = LocationManager.GPS_PROVIDER
            } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) { //Google服务被墙不可用
                //网络定位的精准度稍差，但耗电量比较少。
                println("=====NETWORK_PROVIDER=====")
                locationProvider = LocationManager.NETWORK_PROVIDER
            } else {
                println("=====NO_PROVIDER=====")
                // 当没有可用的位置提供器时，弹出Toast提示用户
                val intent = Intent()
                intent.action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
                mContext!!.startActivity(intent)
                return
            }

            //3.获取上次的位置，一般第一次运行，此值为null
            location = locationManager!!.getLastKnownLocation(locationProvider)
            if (location != null) {
                // 显示当前设备的位置信息
                println("==显示当前设备的位置信息==")
                showLocation()
            } else { //当GPS信号弱没获取到位置的时候可从网络获取
                println("==Google服务被墙的解决办法==")
                getLngAndLatWithNetwork() //Google服务被墙的解决办法
            }
            // 监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
            //LocationManager 每隔 5 秒钟会检测一下位置的变化情况，当移动距离超过 10 米的时候，
            // 就会调用 LocationListener 的 onLocationChanged() 方法，并把新的位置信息作为参数传入。
            locationManager!!.requestLocationUpdates(locationProvider, 5000, 10f, locationListener)
        }

        //获取经纬度
        private fun showLocation() {
            if (location == null) {
                getLocation()
            } else {
                val latitude: Double = location!!.getLatitude() //纬度
                val longitude: Double = location!!.getLongitude() //经度
                //            for(AddressCallback addressCallback:addressCallbacks){
//                addressCallback.onGetLocation(latitude,longitude);
//            }
                if (addressCallback != null) {
                    addressCallback!!.onGetLocation(latitude, longitude)
                }
                getAddress(latitude, longitude)
            }
        }

        private fun getAddress(
            latitude: Double,
            longitude: Double
        ) {
            //Geocoder通过经纬度获取具体信息
            val gc = Geocoder(mContext, Locale.getDefault())
            try {
                val locationList: List<Address>? =
                    gc.getFromLocation(latitude, longitude, 1)
                if (locationList != null) {
                    val address: Address = locationList[0]
                    val countryName: String = address.getCountryName() //国家
                    val countryCode: String = address.getCountryCode()
                    val adminArea: String = address.getAdminArea() //省
                    val locality: String = address.getLocality() //市
                    val subLocality: String = address.getSubLocality() //区
                    val featureName: String = address.getFeatureName() //街道
                    var i = 0
                    while (address.getAddressLine(i) != null) {
                        val addressLine: String = address.getAddressLine(i)
                        //街道名称:广东省深圳市罗湖区蔡屋围一街深圳瑞吉酒店
                        println("addressLine=====$addressLine")
                        i++
                    }
                    if (addressCallback != null) {
                        addressCallback!!.onGetAddress(address)
                    }
                    //                for(AddressCallback addressCallback:addressCallbacks){
//                    addressCallback.onGetAddress(address);
//                }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        private fun removeLocationUpdatesListener() {
            if (locationManager != null) {
                locationManager!!.removeUpdates(locationListener)
            }
        }


        private val locationListener: LocationListener = object : LocationListener {

            // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
            override fun onStatusChanged(
                provider: String,
                status: Int,
                arg2: Bundle
            ) {
            }

            // Provider被enable时触发此函数，比如GPS被打开
            override fun onProviderEnabled(provider: String) {}

            // Provider被disable时触发此函数，比如GPS被关闭
            override fun onProviderDisabled(provider: String) {}

            //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
            override fun onLocationChanged(objects: Location){

            }
        }

        //从网络获取经纬度
        private fun getLngAndLatWithNetwork() {
            //添加用户权限申请判断
            if (ActivityCompat.checkSelfPermission(
                    mContext!!,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    mContext!!,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            val locationManager =
                mContext!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                10f,
                locationListener
            )
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            showLocation()
        }

        interface AddressCallback {
            fun onGetAddress(address: Address?)
            fun onGetLocation(lat: Double, lng: Double)
        }
    }
}