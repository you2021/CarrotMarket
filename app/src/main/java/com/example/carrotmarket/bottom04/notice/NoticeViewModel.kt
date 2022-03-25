package com.example.carrotmarket.bottom04.notice

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
import java.util.ArrayList

class NoticeViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ArrayList<NoticeItem>>()
    val result : LiveData<ArrayList<NoticeItem>>
        get() = _result

    fun noticeFromServer(){
        retrofit.notice().enqueue(object : Callback<ArrayList<NoticeItem>> {
            override fun onResponse(call: Call<ArrayList<NoticeItem>>, response: Response<ArrayList<NoticeItem>>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("notice", "notice : ${response.body()!!.size}")
                }else {
                    Log.d("loginResultFail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<NoticeItem>>, t: Throwable) {
                Log.i("notice","notice fail : ${(t.message).toString()}")
            }
        })
    }



}