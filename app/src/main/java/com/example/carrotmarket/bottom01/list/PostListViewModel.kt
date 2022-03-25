package com.example.carrotmarket.bottom01.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrotmarket.RetrofitHelper
import com.example.carrotmarket.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 면접 가기전에 class 명 바꾸기
class PostListViewModel : ViewModel() {

    val retrofit: RetrofitService = RetrofitHelper.getRetrofitInstanceGson()
        .create(RetrofitService::class.java)

    private val _Result = MutableLiveData<ArrayList<PostListItem>>()
    val Result : LiveData<ArrayList<PostListItem>>
        get() = _Result

    fun getlistFromServer(){
        retrofit.postList().enqueue(object : Callback<ArrayList<PostListItem>> {
            override fun onResponse(call: Call<ArrayList<PostListItem>>, response: Response<ArrayList<PostListItem>>) {

                if (response.isSuccessful){
                    _Result.value = response.body()
                    Log.d("resultValue", "${response.body()!!.size}")
                }else {
                    Log.d("Fail : ", "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<PostListItem>>, t: Throwable) {
                Log.d("Fail : "," ${(t.message).toString()}")
            }
        })
    }


//    fun getErrorResponse(error : ResponseBody): ErrorRes
}