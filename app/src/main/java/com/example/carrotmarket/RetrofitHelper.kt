package com.example.carrotmarket

import com.google.gson.GsonBuilder
import okhttp3.JavaNetCookieJar
//import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.CookieManager

object RetrofitHelper {

    private const val BASE_URL = "http://172.30.1.3:4000/"
    //192.168.200.115 : home
    //172.30.1.6 : caffe
    //172.30.1.30
    //172.30.1.46

    val client = OkHttpClient.Builder()
        .cookieJar(JavaNetCookieJar(CookieManager())) //쿠키매니저 연결
        .build()

    var gson = GsonBuilder().setLenient().create()

    fun getRetrofitInstanceGson() = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

}



