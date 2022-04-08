package com.example.carrotmarket.bottom03.chattingroom

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.bottom01.list.PostListItem
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import io.socket.client.IO
import io.socket.emitter.Emitter
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ChattingRoomViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ArrayList<ChattingRoomItem>>()
    val result : LiveData<ArrayList<ChattingRoomItem>>
        get() = _result

    fun chattingRoomFromServer(){
        retrofit.getRoom().enqueue(object : Callback<ArrayList<ChattingRoomItem>> {
            override fun onResponse(call: Call<ArrayList<ChattingRoomItem>>, response: Response<ArrayList<ChattingRoomItem>>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("resultValue", "${response.body()}")
                }else {
                    Log.d("Fail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<ChattingRoomItem>>, t: Throwable) {
                Log.d("resultValue", "fail : ${t.message}")
            }
        })
    }
}