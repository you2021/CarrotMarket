package com.example.carrotmarket.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.ResultItem
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ResultItem>()
    val result : LiveData<ResultItem>
        get() = _result

    fun kakaoInfoToServer(id:String, pw:String, name:String){
        retrofit.kakao(id,pw, name).enqueue(object :
            Callback<ResultItem> {
            override fun onResponse(call: Call<ResultItem>, response: Response<ResultItem>) {
                Log.d("kakao", "${response.body()}")
//                if (response.isSuccessful){
//                    _result.value = response.body()
//                    Log.d("kakao", "${response.body()}")
//                }else {
//                    Log.d(" kakao Fail : ", "${response.errorBody()}")
//                }
            }

            override fun onFailure(call: Call<ResultItem>, t: Throwable) {
                Log.i("kakao","fail : ${(t.message).toString()}")
            }
        })
    }
}