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

class NoticeDetailViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<NoticeItem>()
    val result : LiveData<NoticeItem>
        get() = _result

    fun noticeDetailFromServer(num : Int){
        retrofit.settingDetail(num).enqueue(object : Callback<NoticeItem> {
            override fun onResponse(call: Call<NoticeItem>, response: Response<NoticeItem>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("noticeDetail", "notice : ${response.body()!!}")
                }else {
                    Log.d("loginResultFail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<NoticeItem>, t: Throwable) {
                Log.i("noticeDetail","notice fail : ${(t.message).toString()}")
            }
        })
    }



}