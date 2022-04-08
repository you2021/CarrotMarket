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

class ChattingInitViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ResultItem>()
    val result : LiveData<ResultItem>
        get() = _result


    fun chattingInitToServer(your_id :String){
        retrofit.socketInit(your_id).enqueue(object : Callback<ResultItem> {
            override fun onResponse(call: Call<ResultItem>, response: Response<ResultItem>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("success", "${response.body()!!}")
                }else {
                    Log.d("Fail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ResultItem>, t: Throwable) {
                Log.d("Fail : "," ${(t.message).toString()}")
            }
        })
    }
}