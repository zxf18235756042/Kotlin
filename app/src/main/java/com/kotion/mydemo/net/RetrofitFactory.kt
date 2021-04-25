package com.kotion.mydemo.net

import android.util.Log
import com.kotion.mydemo.base.BaseApi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {
    companion object{
        val instance:RetrofitFactory by lazy { RetrofitFactory() }
    }
    private val interceptor: Interceptor
    private val retrofit:Retrofit

    //初始化
    init {
        //通用拦截
        interceptor= Interceptor {
            chain ->  val request=chain.request()
            .newBuilder()
            .addHeader("charset","UTF-8")
            .build()
            chain.proceed(request)
        }

        //Retrofit实例化
        retrofit=Retrofit.Builder()
            .baseUrl(BaseApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initClient())
            .build()
    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(MoreBaseUrlInterceptor())
            .addInterceptor(LoggingInterceptor())
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    class LoggingInterceptor:Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request =chain.request()
            var response=chain.proceed(request)
            var responseBody=response.peekBody(Long.MAX_VALUE)
            Log.i("responseBody", responseBody.string())
            return response
        }
    }
    class MoreBaseUrlInterceptor:Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            var req =chain.request()
            var oldUrl=req.url()
            var builder =req.newBuilder()
            var newUrl=req.header("newurl")
            if (newUrl!=null && newUrl!!.isNotEmpty()){
                var baseUrl=HttpUrl.parse(newUrl)
                var newHttpUrl=HttpUrl.Builder()
                    .scheme(baseUrl!!.scheme())
                    .host(baseUrl!!.host())
                    .port(baseUrl!!.port())
                    .encodedPath(oldUrl.encodedPath())
                    .encodedQuery(oldUrl.encodedQuery())
                    .build()
                var newReq =builder.url(newHttpUrl).build()
                return chain.proceed(newReq)
            }
            return chain.proceed(req)
        }
    }
    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }
}