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

    private const val BASE_URL = "http://192.168.200.115/"
    //192.168.200.115 : home  192.168.25.8
    //172.30.1.6 : caffe
    //15.165.237.127  AWS
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



