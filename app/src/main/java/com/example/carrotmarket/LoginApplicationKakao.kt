package com.example.carrotmarket

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class LoginApplicationKakao: Application() {

    lateinit var instance: LoginApplicationKakao

    override fun onCreate() {
        super.onCreate()
        instance = this

        KakaoSdk.init(this, "6e79090ffd244797258921cdc6e84985")
    }
}