package com.example.carrotmarket.bottom04.myPost

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import com.example.carrotmarket.bottom01.list.PostListItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 면접 가기전에 class 명 바꾸기
class MyPostViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _result = MutableLiveData<ArrayList<PostListItem>>()
    val result : LiveData<ArrayList<PostListItem>>
        get() = _result

    fun getMyPostFromServer(){
        retrofit.myPost().enqueue(object : Callback<ArrayList<PostListItem>> {
            override fun onResponse(call: Call<ArrayList<PostListItem>>, response: Response<ArrayList<PostListItem>>) {

                if (response.isSuccessful){
                    _result.value = response.body()
                    Log.d("resultValue", "myposot : ${response.body()!!.size}")
                }else {
                    Log.d("Fail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<PostListItem>>, t: Throwable) {
                Log.d("Fail : "," ${(t.message).toString()}")
            }
        })
    }
}