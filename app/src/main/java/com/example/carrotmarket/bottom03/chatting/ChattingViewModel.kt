package com.example.carrotmarket.bottom03.chatting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ChattingViewModel : ViewModel() {
    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ArrayList<ChattingItem>>()
    val result : LiveData<ArrayList<ChattingItem>>
        get() = _result

    fun chattingFromServer(room:Int){
        retrofit.chattingList(room).enqueue(object : Callback<ArrayList<ChattingItem>> {
            override fun onResponse(call: Call<ArrayList<ChattingItem>>, response: Response<ArrayList<ChattingItem>>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("ChattingItem", "${response.body()!!.size}")
                }else {
                    Log.d("Fail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<ChattingItem>>, t: Throwable) {
                Log.d("Fail : "," ${(t.message).toString()}")
            }
        })
    }
}