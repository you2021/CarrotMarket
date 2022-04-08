package com.example.carrotmarket.bottom03

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.ResultItem
import com.example.carrotmarket.bottom01.list.PostListItem
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import io.socket.client.IO
import io.socket.emitter.Emitter
import org.json.JSONObject
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

    fun chattingFromServer(room:String){
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