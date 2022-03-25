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

// 면접 가기전에 class 명 바꾸기
class LoginViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _idResult = MutableLiveData<ResultItem>()
    val idResult : LiveData<ResultItem>
        get() = _idResult

    fun idToServer(id:String,pw:String,){
        retrofit.login(id!!, pw).enqueue(object : Callback<ResultItem> {
            override fun onResponse(call: Call<ResultItem>, response: Response<ResultItem>) {

                if (response.isSuccessful){
                    _idResult.value = response.body()
                    Log.d("loginResult", "login id : ${response.body()}")
                }else {
                    Log.d("loginResultFail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ResultItem>, t: Throwable) {
                Log.i("loginResult","fail : ${(t.message).toString()}")
            }
        })
    }



}